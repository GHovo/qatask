package common;

import constants.logmessage.Constants;
import constants.logmessage.DriverConstants;
import constants.logmessage.Error;
import constants.logmessage.Info;
import exceptions.APIException;
import exceptions.IncorrectEnvironmentSetup;
import org.apache.log4j.Logger;
import org.testng.IClass;
import org.testng.ITestResult;

import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Util {
    private static final Logger LOG = Logger.getLogger(Util.class);

    private Util() {
    }

    /**
     * Get the OS name of the machine
     *
     * @return
     */
    public static final String getOsName() {
        return System.getProperty(DriverConstants.OS_NAME_PROPERTY)
                .toUpperCase().split(" ")[0];
    }

    /**
     * Executes shell command with specified arguments
     *
     * @param command(command with arguments)
     * @return command result
     * @throws IncorrectEnvironmentSetup
     */
    public static final String executeCommand(String... command)
            throws IncorrectEnvironmentSetup {
        StringBuilder output = new StringBuilder();
        Process p;
        String stringCommand = Arrays.toString(command);
        try {
            LOG.debug(String.format(Info.EXECUTE_SHELL_COMMAND.getLog(), stringCommand));
            p = Runtime.getRuntime().exec(command);
        } catch (IOException ex) {
            throw new IncorrectEnvironmentSetup(String.format(Error.CAN_NOT_EXECUTE_COMMAND.getLog(), stringCommand));
        }
        Scanner sc = new Scanner(p.getInputStream());
        while (sc.hasNext()) {
            output.append(sc.nextLine()).append("\n");
        }
        return output.toString();
    }

    /**
     * Gets the version of any program with path
     *
     * @param appPath
     * @return
     * @throws IncorrectEnvironmentSetup
     */
    public static String getAppVersion(String appPath)
            throws IncorrectEnvironmentSetup {
        return executeCommand(appPath, "--version");
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface UseAsTestRailId {
        int testRailId() default 0;
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface getDefects {
        String defectId() default "";
    }

    public static void addResultsInTestRun(ITestResult testResult, String testRunId, String testRailUrl,
                                           String encodedUsername, String encodedPassword)
            throws NoSuchMethodException, IOException, APIException {
        if (!testRunId.equals("")) {
            IClass obj = testResult.getTestClass();
            Class newObj = obj.getRealClass();
            Method testMethod = newObj.getMethod(testResult.getName());
            APIClient client = new APIClient(testRailUrl);
            client.setUser(APIClient.decodeString(encodedUsername));
            client.setPassword(APIClient.decodeString(encodedPassword));
            Map<String, String> data = new HashMap<>();
            if (testMethod.isAnnotationPresent(UseAsTestRailId.class)) {
                UseAsTestRailId useAsTestName = testMethod.getAnnotation(UseAsTestRailId.class);
                String testId = Integer.toString(useAsTestName.testRailId());
                if (testResult.getStatus() == ITestResult.SUCCESS) {
                    data.put(Constants.testRailConstants.STATUS_ID.toString(),
                            Constants.testRailConstants.STATUS_ID_PASS.toString());
                } else if (testResult.getStatus() == ITestResult.FAILURE) {
                    data.put(Constants.testRailConstants.STATUS_ID.toString(),
                            Constants.testRailConstants.STATUS_ID_FAIL.toString());
                    if (testMethod.isAnnotationPresent(getDefects.class)) {
                        getDefects defect = testMethod.getAnnotation(getDefects.class);
                        String defectId = defect.defectId();
                        data.put(Constants.testRailConstants.DEFECTS.toString(), defectId);
                    }
                }
                client.sendPost(String.format(Constants.SEND_DATA, testRunId, testId), data);
            }
        }
    }
}
