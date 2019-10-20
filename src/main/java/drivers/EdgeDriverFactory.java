package drivers;

import constants.logmessage.Constants;
import constants.logmessage.DriverConstants;
import constants.logmessage.Info;
import exceptions.IncorrectEnvironmentSetup;
import interfaces.DriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.logging.Level;

public class EdgeDriverFactory implements DriverFactory {

    private static final Logger LOG = Logger.getLogger(EdgeDriverFactory.class);

    private static WebDriver driver = null;

    private static boolean isDefault;

    private static boolean hasProxy = false;

    private EdgeOptions edgeOptions;
    private DesiredCapabilities capabilities;

    public EdgeDriverFactory() {
        isDefault = true;
        edgeOptions = new EdgeOptions();
        capabilities = DesiredCapabilities.edge();
    }

    @Override
    public WebDriver create(boolean hasProxy) throws IncorrectEnvironmentSetup {
        EdgeDriverFactory.hasProxy = hasProxy;
        if (isDefault) {
            initDriverDefaultCapabilitiesAndOptions();
        }
        LOG.info(String.format(Info.CREATE_WEB_DRIVER.getLog(),
                DriverConstants.BrowserNames.EDGE, Constants.KeyWords.EXISTING.getKeyWord()));
        initDriver();
        driver = new EdgeDriver(edgeOptions);
        return driver;
    }

    /*
     * Set all defined Options
     */
    @Override
    public void initDriverDefaultCapabilitiesAndOptions() throws IncorrectEnvironmentSetup {
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.INFO);

        edgeOptions.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        edgeOptions.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
        capabilities.setJavascriptEnabled(true);
        edgeOptions.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
                UnexpectedAlertBehaviour.IGNORE);
        edgeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        edgeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        edgeOptions.setCapability(CapabilityType.SUPPORTS_NETWORK_CONNECTION, true);

        edgeOptions.merge(capabilities);
    }

    public static boolean getProxy() {
        return hasProxy;
    }

    @Override
    public void resetDefaultCapabilitiesAndOptions() {
        edgeOptions = new EdgeOptions();
    }

    @Override
    public void setCapability(String capabilityName, String capabilityValue) {
        isDefault = false;
        edgeOptions.setCapability(capabilityName, capabilityValue);
    }

    @Override
    public String getCapability(String capabilityName) {
        return edgeOptions.getCapability(capabilityName).toString();
    }

    private void initDriver() {
        WebDriverManager.edgedriver().setup();
    }
}
