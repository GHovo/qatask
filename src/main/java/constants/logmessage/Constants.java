package constants.logmessage;

public class Constants {
    public static final String PROPERTY_BROWSER = "browser";
    public static final String IPHONE = "iPhone";
    public static final String DEVICE_NAME = "deviceName";
    public static final String PROPERTY_DEVICE_ID = "deviceId";
    public static final String PROPERTY_DEVICE_OS_VERSION = "osVersion";
    public static final String PROPERTY_PLATFORM = "platform";
    public static final String PROPERTY_DEVICE_TYPE = "deviceType";
    public static final String PROPERTY_EXPECTED_URL = "expectedUrl";
    public static final String PROPERTY_URL = "url";

    public static final String USER = "user";
    public static final String KEY = "key";
    public static final String SERVER = "server";
    public static final String CAPABILITIES = "capabilities";
    public static final String APP = "app";
    public static final String TRUE = "true";
    public static final String TEST_NAME = "testName";
    public static final String NAME = "name";

    public static final String DEVICE_TYPE_DEFAULT_VALUE = "desktop";
    public static final String FALSE = "false";
    public static final String PLATFORM_DEFAULT_VALUE = "linux";
    public static final String CHROME = "chrome";

    public static String PROXY = "proxy";
    public static final String PLATFORM = "linux";


    public static final String SCREENSHOT_FILE_PATH="test-output/screenshots";
    public static final String RESULTS_FILE_PATH="test-output/testng-results.xml";

    //Default timeout in seconds
    public static final int DEFAULT_TIMEOUT = 15;
    public static final int DEFAULT_TIMEOUT_SCROLLING = 15;
    public static final int LONG_TIMEOUT_SCROLLING = 25;

    // Default browser size
    public static final int BROWSER_DEFAULT_HEIGHT = 960;
    public static final int BROWSER_DEFAULT_WIDTH = 630;

    //TestRail Constants
    public static final String SEND_DATA = "add_result_for_case/%s/%s";

    private Constants() {
    }

    public enum MethodNames {
        id, name, className, tagName, linkText, text, cssSelector, xpath
    }
    public enum KeyWords {

        MOBILE("mobile"),
        DESKTOP("desktop"),
        COMPLETE("complete"),
        PNG("image/png"),
        EXISTING("existing");
        private final String word;

        KeyWords(String word) {
            this.word = word;
        }

        public String getKeyWord() {
            return word;
        }
    }
    public enum testRailConstants {

        STATUS_ID("status_id"),
        STATUS_ID_PASS("1"),
        STATUS_ID_FAIL("5"),
        DEFECTS("defects");

        private final String testRailConstant;

        testRailConstants(String testRailConstant) {
            this.testRailConstant = testRailConstant;
        }

        @Override
        public String toString() {
            return testRailConstant;
        }
    }
}
