package constants.logmessage;

public enum Debug {
    DRIVER("DriverExecutor"),
    LINK_TEXT("Link Text is: "),
    DRIVER_IS("Driver: %s."),
    DRIVER_PATH("Path to driver : %s"),
    BROWSER_PATH("browser Path : %s"),
    CREATE_DRIVER_INSTANCE("Creating web driver instance"),
    CREATE_DRIVER_EXECUTOR("Created DriverExecutor object  : %s"),
    FINISH_SETUP("Finish setting up DriverExecutor : %s"),
    INITIALIZE("Start initialize");
    private final String log;

    Debug(String log) {
        this.log = log;
    }

    public String getLog() {
        return log;
    }
}
