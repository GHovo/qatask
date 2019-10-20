package drivers;

import common.Util;
import constants.logmessage.Constants;
import constants.logmessage.DriverConstants;
import constants.logmessage.Error;
import constants.logmessage.Info;
import exceptions.IncorrectEnvironmentSetup;
import interfaces.DriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.logging.Level;

public class FirefoxDriverFactory implements DriverFactory {

    private static final Logger LOG = Logger.getLogger(FirefoxDriverFactory.class);

    private static WebDriver driver = null;

    private static boolean isDefault;

    private static boolean hasProxy = false;

    private FirefoxOptions firefoxOptions;
    private DesiredCapabilities capabilities;

    public FirefoxDriverFactory() {
        isDefault = true;
        firefoxOptions = new FirefoxOptions();
        capabilities = DesiredCapabilities.firefox();
    }

    @Override
    public WebDriver create() throws IncorrectEnvironmentSetup {
        FirefoxDriverFactory.hasProxy = hasProxy;
        if (isDefault) {
            initDriverDefaultCapabilitiesAndOptions();
        }
        LOG.info(String.format(Info.CREATE_WEB_DRIVER.getLog(),
                DriverConstants.BrowserNames.FIREFOX, Constants.KeyWords.EXISTING.getKeyWord()));
        initDriver();
        driver = new FirefoxDriver(firefoxOptions);
        driver.manage().window().maximize();
        return driver;
    }

    public void setFirefoxProfile () {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setAcceptUntrustedCertificates(true);
        profile.setAssumeUntrustedCertificateIssuer(false);
        capabilities.setCapability(FirefoxDriver.PROFILE, profile);
    }

    @Override
    public void initDriverDefaultCapabilitiesAndOptions() throws IncorrectEnvironmentSetup {
        for (Options option : Options.values()) {
            firefoxOptions.addArguments(option.getOption());
        }
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.INFO);
        capabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        this.setFirefoxProfile();
        capabilities.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
        capabilities.setJavascriptEnabled(true);
        capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
                UnexpectedAlertBehaviour.IGNORE);
        capabilities.setCapability(CapabilityType.SUPPORTS_NETWORK_CONNECTION, true);

        firefoxOptions.merge(capabilities);
    }

    public static boolean hasProxy() {
        return hasProxy;
    }

    @Override
    public void resetDefaultCapabilitiesAndOptions() {
        firefoxOptions = new FirefoxOptions();
        capabilities = DesiredCapabilities.firefox();
    }

    @Override
    public void setCapability(String capabilityName, String capabilityValue) {
        isDefault = false;
        capabilities.setCapability(capabilityName, capabilityValue);
    }

    @Override
    public String getCapability(String capabilityName) {
        return capabilities.getCapability(capabilityName).toString();
    }

    private void initDriver() {
        WebDriverManager.firefoxdriver().setup();
    }

    private String getBrowserPath() throws IncorrectEnvironmentSetup {
        String osName = Util.getOsName();
        String path = null;
        try {
            path = (BrowserPaths.valueOf(osName)).getChromePath();
        } catch (IllegalArgumentException ex) {
            driver = null;
            throw new IncorrectEnvironmentSetup(
                    String.format(Error.UNSUPPORTED_OS.getLog(), osName));
        }
        return path;
    }

    private enum BrowserPaths {
        LINUX("/usr/bin/firefox"), MAC("/Applications/Firefox.app/Contents/MacOS/firefox");

        private final String firefoxPath;

        BrowserPaths(String firefoxPath) {
            this.firefoxPath = firefoxPath;
        }

        public String getChromePath() {
            return firefoxPath;
        }


    }

    public enum Options {
        SAVE_MODE("--safe-mode");

        private final String option;

        Options(String chromePath) {
            this.option = chromePath;
        }

        public String getOption() {
            return option;
        }
    }
}
