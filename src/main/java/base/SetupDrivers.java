package base;

import constants.logmessage.Constants;
import constants.logmessage.DriverConstants;
import constants.logmessage.Error;
import drivers.*;
import exceptions.IncorrectEnvironmentSetup;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;

import static constants.logmessage.Constants.*;
import static constants.logmessage.DriverConstants.OsNames.MACOS;

public class SetupDrivers {
    public static WebDriver setup() throws IncorrectEnvironmentSetup {
        return initializeLocalDriver();
    }


    public static WebDriver initializeLocalDriver() throws IncorrectEnvironmentSetup {
        String osName = System.getProperty(PROPERTY_PLATFORM).toUpperCase();
        String browserName = System.getProperty(PROPERTY_BROWSER);

        switch (DriverConstants.OsNames.valueOf(osName)) {
            case MACOS:
                if (!DriverConstants.macBrowsers.contains(browserName)) {
                    throw new IncorrectEnvironmentSetup(String
                            .format(Error.UNSUPPORTED_BROWSER
                                    .getLog(), browserName));
                }
                break;
            case LINUX:
                if (!DriverConstants.linuxBrowsers.contains(browserName)) {
                    throw new IncorrectEnvironmentSetup(String
                            .format(Error.UNSUPPORTED_BROWSER
                                    .getLog(), browserName));
                }
                break;
            case WINDOWS:
                if (!DriverConstants.windowsBrowsers.contains(browserName)) {
                    throw new IncorrectEnvironmentSetup(String
                            .format(Error.UNSUPPORTED_BROWSER
                                    .getLog(), browserName));
                }
                break;
            default:
                throw new IncorrectEnvironmentSetup(String.format(
                        Error.UNSUPPORTED_OS.getLog(),
                        DriverConstants.OsNames.valueOf(osName)));
        }
        return createWebDriver(browserName);
    }

    private static WebDriver createWebDriver(String browserName)
            throws IncorrectEnvironmentSetup {
        switch (browserName) {
            case DriverConstants.CHROME:
                return new ChromeDriverFactory().create();
            case DriverConstants.FIREFOX:
                return new FirefoxDriverFactory().create();
            case DriverConstants.EDGE:
                return new EdgeDriverFactory().create();
            case DriverConstants.SAFARI:
                return new SafariDriverFactory().create();
            case DriverConstants.CHROMIUM_EDGE:
                return new ChromiumEdgeDriverFactory().create();
            default:
                throw new IncorrectEnvironmentSetup(String
                        .format(Error.UNSUPPORTED_BROWSER
                                .getLog(), browserName));
        }
    }
}
