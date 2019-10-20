package constants.logmessage;

public enum Error {
    UNSUPPORTED_BROWSER("The automation doesn't support %s browser "),
    CAN_NOT_FIND_BROWSER("Unable to find %s browser on %s machine"),
    CAN_NOT_EXECUTE_COMMAND("Couldn't exectute the %s command"),
    UNSUPPORTED_SETUP("Invalid browser setup. Please make sure the appium driver is on."),
    UNSUPPORTED_DEVICE_SETUP("Unable to find an active device or emulator with '%s' OS  and '%s' name"),
    UNSUPPORTED_OS("The automation doesn't support %s OS"),
    UNSUPPORTED_URL_SETUP("Invalid url setup. Please make sure the given url is correct."),
    MISSING_REQUIRED_PARAMETER("Unable to find %s parameter from config"),
    UNSUPPORTED_DRIVER_VERSION("The automation doesn't support %s driver version"),
    CANNOT_LOAD_THE_PAGE("Unable to load page"),
    CANNOT_POEN_THE_PAGE("Unable to open %s page"),
    WEB_DRIVER_NULL("Web driver is null object"),
    CANNOT_CREATE_FILE("'%s' file is not created."),
    CANNOT_STOP_SERVER("Can't stop proxy server."),
    CANNOT_SWITCH_TAB("Unable to switch a new tab"),
    CANNOT_CLOSE_TAB("Unable to close the tab"),
    WAIT_TIMEOUT_ELEMENT_IS_NOT_CLICKABLE_BY_CSS("%s element by %s CSS selector "
            + "is not clickable after %d timout"),
    WAIT_TIMEOUT_ELEMENT_IS_NOT_VISABLE_BY_CSS("%s element by %s CSS selector "
            + "is not visible after %d timout"),
    WAIT_TIMEOUT_ELEMENT_IS_NOT_CLICKABLE("%s element is not clickable after %d timout"),
    WAIT_TIMEOUT_ELEMENT_IS_NOT_PRESENT("%s element by '%s' CSS selector"
            + " is not present after %d timeout"),
    WAIT_TIMEOUT_ELEMENT_IS_PRESENT("Element by '%s' %s is not disappear after %d timeout"),
    CANNOT_FIND_ELEMENT_BY_TYPE("Unable to fined '%s' element by '%s' type"),
    WAIT_TIMEOUT_ATTRIBUTE_VALUE_IS_NOT_CHANGED("The value of '%s' attribute is not changed after %d timeout"),
    GIVEN_INCORRECT_WEBELEMENT("The given webelement is incorrect"),
    POST_USER_API_FAILS("Failed to create user. Response code is %s."),
    USER_GUID_IS_MISSING_FROM_POST_USER_API_RESPONSE("The guid is missing from the response body: %s"),
    AUTHORIZATION_API_FAILS("Authorization API call fails. Response code is %s."),
    PAGE_URL_IS_NOT_CORRECT("The page URL is '%s' instead of '%s'"),
    WAIT_TIMEOUT_URL_DOES_NOT_CONTAIN("The page URL does not contain %s after %d timeout"),
    POST_START_VHS_API_FAILS("The start VHS API call fails. Response code is %s."),
    POST_CONTINUE_VHS_API_FAILS("The continue VHS API call fails. Response code is %s."),
    POST_END_VHS_API_FAILS("The end VHS API call fails. Response code is %s.");

    private final String log;

    Error(String log) {
        this.log = log;
    }

    public String getLog() {
        return log;
    }

}
