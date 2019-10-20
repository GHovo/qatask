package constants.logmessage;

import java.util.Arrays;
import java.util.List;

public class DriverConstants {
    public static final String CHROME = "chrome";
    public static final String FIREFOX = "firefox";
    public static final String SAFARI = "Safari";
    public static final String EDGE = "edge";
    public static final String CHROMIUM_EDGE = "edgeDev";
    public static final String OS_NAME_PROPERTY = "os.name";
    public static final List<String> androidBrowsers = Arrays.asList(CHROME);
    public static final List<String> iOSBrowsers = Arrays.asList(CHROME,SAFARI.toLowerCase());
    public static final List<String> macBrowsers = Arrays.asList(CHROME, SAFARI, FIREFOX, CHROMIUM_EDGE);
    public static final List<String> linuxBrowsers = Arrays.asList(CHROME, FIREFOX);
    public static final List<String> windowsBrowsers = Arrays.asList(CHROME, FIREFOX, EDGE, CHROMIUM_EDGE);

    private DriverConstants() {
    }

    public enum AndroidDeviceParameters {
        AUTOMATION_NAME("UiAutomator2"),
        APP_ACTIVITY("MainActivity");
        private final String word;
        AndroidDeviceParameters(String word) {
            this.word = word;
        }
        public String getKeyWord() {
            return word;
        }
    }

    public enum iOSDeviceParameters {
        AUTOMATION_NAME("XCUITest");
        private final String word;
        iOSDeviceParameters(String word) {
            this.word = word;
        }
        public String getKeyWord() {
            return word;
        }
    }

    public enum DeviceParameters {
        PACKAGE("com.openfit.openfit"),
        NO_RESET("true");
        private final String word;
        DeviceParameters(String word) {
            this.word = word;
        }
        public String getKeyWord() {
            return word;
        }
    }

    public enum CapabilitiesConstants {
        deviceName, platformName, platformVersion, appPackage, appActivity, udid, appPath, isLocal, deviceType, noReset, bundleId
    }

    public enum BrowserNames {
        SAFARI, CHROME, FIREFOX, EDGE
    }

    public enum OsNames {
        MACOS, LINUX, ANDROID, WINDOWS, IOS
    }

    public enum OsCapability {
        iOS, Android
    }



    public enum DriverPropertyName {

        SAFARI("webdriver.safari.driver"),
        EDGE("webdriver.edge.driver"),
        CHROME("webdriver.chrome.driver");

        private final String driverPropertyName;

        DriverPropertyName(String driverPropertyName) {
            this.driverPropertyName = driverPropertyName;
        }

        public String getDriverPropertyName() {
            return driverPropertyName;
        }
    }
}
