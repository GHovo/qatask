package drivers;

import constants.logmessage.Constants;
import constants.logmessage.DriverConstants;
import constants.logmessage.Info;
import interfaces.DriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;

import java.util.logging.Level;

public class ChromiumEdgeDriverFactory implements DriverFactory {

    private static final Logger LOG = Logger.getLogger(ChromiumEdgeDriverFactory.class);

    private static WebDriver driver = null;

    private static boolean isDefault;

    private static final String CHROMIUM_EDGE_VERSION = "77.0.237.0";

    private ChromeOptions chromeOptions;

    public ChromiumEdgeDriverFactory() {
        isDefault = true;
        chromeOptions = new ChromeOptions();
    }

    @Override
    public WebDriver create() {
        if (isDefault) {
            initDriverDefaultCapabilitiesAndOptions();
        }
        LOG.info(String.format(Info.CREATE_WEB_DRIVER.getLog(),
                DriverConstants.BrowserNames.EDGE, Constants.KeyWords.EXISTING.getKeyWord()));
        initDriver();
        System.setProperty(DriverConstants.DriverPropertyName.CHROME.getDriverPropertyName(), System.getProperty(DriverConstants.DriverPropertyName.EDGE.getDriverPropertyName()));
        driver = new ChromeDriver(chromeOptions);
        return driver;
    }

    /*
     * Set all defined Options
     */
    @Override
    public void initDriverDefaultCapabilitiesAndOptions() {
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.INFO);

        chromeOptions.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        chromeOptions.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
        chromeOptions.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
                UnexpectedAlertBehaviour.IGNORE);
        chromeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        chromeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        chromeOptions.setCapability(CapabilityType.SUPPORTS_NETWORK_CONNECTION, true);

    }

    @Override
    public void resetDefaultCapabilitiesAndOptions() {
        chromeOptions = new ChromeOptions();
    }

    @Override
    public void setCapability(String capabilityName, String capabilityValue) {
        isDefault = false;
        chromeOptions.setCapability(capabilityName, capabilityValue);
    }

    @Override
    public String getCapability(String capabilityName) {
        return chromeOptions.getCapability(capabilityName).toString();
    }

    private void initDriver()  {
        WebDriverManager.edgedriver().version(CHROMIUM_EDGE_VERSION).setup();
    }
}
