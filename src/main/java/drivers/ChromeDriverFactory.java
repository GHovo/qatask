package drivers;

import common.Util;
import constants.logmessage.*;
import constants.logmessage.Error;
import exceptions.IncorrectEnvironmentSetup;
import interfaces.DriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.logging.Level;

public class ChromeDriverFactory implements DriverFactory {

    private static final Logger LOG = Logger.getLogger(ChromeDriverFactory.class);

    private static WebDriver driver = null;

    private static boolean isDefault;

    private static boolean hasProxy = false;

    private ChromeOptions chromeOptions;
    private DesiredCapabilities capabilities;

    public ChromeDriverFactory() {
        isDefault = true;
        chromeOptions = new ChromeOptions();
        capabilities = DesiredCapabilities.chrome();
    }

    @Override
    public WebDriver create() throws IncorrectEnvironmentSetup {
        if (isDefault) {
            initDriverDefaultCapabilitiesAndOptions();
        }
        LOG.info(String.format(Info.CREATE_WEB_DRIVER.getLog(),
                DriverConstants.BrowserNames.CHROME, Constants.KeyWords.EXISTING.getKeyWord()));
        driver = new ChromeDriver(initDriver(), chromeOptions);
        return driver;
    }

    /*
     * Set all defined Options
     */
    @Override
    public void initDriverDefaultCapabilitiesAndOptions() throws IncorrectEnvironmentSetup {
        for (Options option : Options.values()) {
            chromeOptions.addArguments(option.getOption());
        }
        String browserPath = getBrowserPath();
        LOG.debug(String.format(Debug.BROWSER_PATH.getLog(), browserPath));
        File binaryChrome = new File(browserPath);
        chromeOptions.setBinary(binaryChrome);
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
        chromeOptions.merge(capabilities);
    }

    @Override
    public void resetDefaultCapabilitiesAndOptions() {
        chromeOptions = new ChromeOptions();
        capabilities = DesiredCapabilities.chrome();
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

    /*
     * Set specific option
     */
    public void setOption(Options option) {
        isDefault = false;
        chromeOptions.addArguments(option.getOption());
    }

    private ChromeDriverService initDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriverService.Builder().usingAnyFreePort().build();
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

        LINUX("/usr/bin/google-chrome"), MAC(
                "/Applications/Google Chrome.app/Contents/MacOS/Google Chrome"),
        WINDOWS("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");

        private final String chromePath;

        BrowserPaths(String chromePath) {
            this.chromePath = chromePath;
        }

        public String getChromePath() {
            return chromePath;
        }
    }

    public enum Options {
        // To ignore errors from chrome for using robot
        NOSANDBOX("--no-sandbox"),
        // To open browser in fullscreen
        STARTMAXIMIZED("--start-maximized"),
        // Disable ChromeDriverFactory info bars
        DISABLEINFOBARS("--disable-infobars"),
        // Disable ChromeDriverFactory notifications
        DISABLENOTIFICATIONS("--disable-notifications"),
        // Disable ChromeDriverFactory extensions
        DISABLEEXTENSIONS("--disable-extensions"),
        // Disable ChromeDriverFactory gpu
        DISABLEGPU("--disable-gpu"),
        // Enable ChromeDriverFactory automation
        ENABLEAUTOMATION("--enable-automation"),
        // Disable web security for automation
        DISABLEWEBSECURITY("--disable-web-security"),
        // Ignore certificate errors
        IGNORECERTIFICATEERRORS("--ignore-certificate-errors"),
        IGNOREURLFETCHER("--ignore-urlfetcher-cert-requests"),
        // Allow Running Insecure Content
        ALLOWRUNNINGINSECURECONTENT("--allow-running-insecure-content"),
        // No yellow security message would appear on screen
        TESTTYPE("test-type"),
        MAXIMIZEWINDOW("window-size=1920,1080"),
        FIX_NEW_TAB_SWITCH_HANGING("--disable-site-isolation-trials");

        private final String option;

        Options(String chromePath) {
            this.option = chromePath;
        }

        public String getOption() {
            return option;
        }
    }

}
