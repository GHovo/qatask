package constants.logmessage;

public enum Info {
    CREATE_WEB_DRIVER("Creating %s webDriver for %s stable browser version."),
    EXECUTE_SHELL_COMMAND("Executing shell command :  %s"),
    INFO_DELETE_COOKIES("Delete all cookies."),
    INFO_CLOSE_BROWSER("Close browser."),
    INFO_CURRENT_DIMENSIONS("Fix page dimensions: %s."),
    INFO_GO_BACK("Go back one page."),
    INFO_REFRESH_PAGE("Refresh current page."),
    INFO_RESTORE_WINDOW_DIMENSIONS("Restore window dimensions."),
    INFO_RESTORE_WINDOW("Restore window"),
    INFO_NEW_DIMENSIONS("The browser new dimensions are [%s, %s]."),
    INFO_PAGE_LOAD_TIME("Page loads with %s second(s)."),
    INFO_EXISTS_IN_NEW_TAB("Check that %s ='%s' is present in new tab."),
    INFO_SWITCH_TO_FRAME("Switch to the frame by '%s' %s"),
    INFO_SWITCH_TO_DEFAULT_FRAME("Switch to the default frame."),
    INFO_SWITCH_TO_WINDOW("Switch to the new window."),
    INFO_SWITCH_TO_DEFAULT_WINDOW("Switch to the default window."),
    INFO_WAIT_PAGE_LOAD("Wait until page loads."),
    INFO_OPEN_PAGE_WITH_URL("Open the page with '%s' url."),
    INFO_ALERT("Alert data: "),
    INFO_NEW_EMAIL("New generated email is %s"),
    INFO_PAGE_URL_IS_CORRECT("The Page URL is correct."),
    SCROLL_TO_TOP("Scroll to the top of the page."),
    SCROLL_TO_BOTTOM("Scroll to the bottom of the page."),
    GET_BROWSER_WIDTH("Get browser inner width."),
    SCROLL_UNTIL_ELEMENT_IS_NOT_DISPLAYED("Scroll to the bottom until element is not displayed"),
    SCROLL_TILL_ELEMENT("Scroll till %s element by %s selector"),
    DEFAULT_WINDOW_LOG("Current window is default."),
    MOUSE_MOVE("Moving the mouse to the %s %s"),
    MOVE_TO_ELEMENT("Move to the '%s' element"),
    MOVE_TO_THE_CENTER_OF_ELEMENT("Move to the center of '%s' element"),
    WAIT_FOR_SPECIFIC_TIME("Wait %s second(s)."),
    WAIT_FOR_ELEMENT("Waiting till element (by '%s' %s) is present on the page. ");

    private final String log;

    Info(String log) {
        this.log = log;
    }

    public String getLog() {
        return log;
    }
}
