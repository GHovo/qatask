package drivers;

import constants.logmessage.Constants;
import constants.logmessage.DriverConstants;
import constants.logmessage.Info;
import exceptions.IncorrectEnvironmentSetup;
import interfaces.DriverFactory;
import org.apache.log4j.Logger;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.util.logging.Level;

public class SafariDriverFactory implements DriverFactory {

    private static final Logger LOG = Logger.getLogger(SafariDriverFactory.class);

    private static WebDriver driver = null;

    private static boolean isDefault;

    private static boolean hasProxy = false;

    private SafariOptions safariOptions;

    private DesiredCapabilities capabilities;

    public SafariDriverFactory() {
        isDefault = true;
        //capabilities = DesiredCapabilities.safari();
    }

    @Override
    public WebDriver create(boolean hasProxy) throws IncorrectEnvironmentSetup {
        SafariDriverFactory.hasProxy = hasProxy;
        LOG.info(String.format(Info.CREATE_WEB_DRIVER.getLog(),
                DriverConstants.BrowserNames.SAFARI, Constants.KeyWords.EXISTING.getKeyWord()));
        if (isDefault) {
            initDriverDefaultCapabilitiesAndOptions();
        }
        driver = new SafariDriver();
        return driver;
    }

    @Override
    public void initDriverDefaultCapabilitiesAndOptions() throws IncorrectEnvironmentSetup {
        resetDefaultCapabilitiesAndOptions();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.INFO);
        capabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        capabilities.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
        capabilities.setJavascriptEnabled(true);
        capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
                UnexpectedAlertBehaviour.IGNORE);
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        capabilities.setCapability(CapabilityType.SUPPORTS_NETWORK_CONNECTION, true);

        safariOptions.merge(capabilities);
    }

    @Override
    public void setCapability(String capabilityName, String capabilityValue) {

    }

    public static boolean hasProxy() {
        return hasProxy;
    }

    @Override
    public String getCapability(String capabilityName) {
        return capabilities.getCapability(capabilityName).toString();
    }

    @Override
    public void resetDefaultCapabilitiesAndOptions() {
        safariOptions = new SafariOptions();
        capabilities = DesiredCapabilities.safari();
    }

}
