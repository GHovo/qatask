package interfaces;

import exceptions.IncorrectEnvironmentSetup;
import org.openqa.selenium.WebDriver;

public interface DriverFactory {
    /*
     * @params hasProxy
     *    - true create driver with a proxy
     *    - false create driver without a proxy
     * @return WebDriver
     *   - Create a web driver for specific.
     * @throws UnsupportedOSName
     * @throws IncorrectEnvironmentSetup
     * @throws UnsupportedDriverVersion
     */
    WebDriver create( )
            throws IncorrectEnvironmentSetup;

    /*
     * initDriverCapabilities all supported options/capabilities with default values
     */
    public void initDriverDefaultCapabilitiesAndOptions() throws IncorrectEnvironmentSetup;

    /**
     * @param capabilityName - the key of capability
     * @param capabilityValue - the value of capability To set an specific
     * capability by capability name and value
     */
    public void setCapability(String capabilityName, String capabilityValue);

    /*
     * @param capabilityName
     * returns the current value of provided capability name
     */
    public String getCapability(String capabilityName);

    /*
     *initDriverCapabiinitDriverCapabilitieslities all options/capabilities with empty values
     */
    public void resetDefaultCapabilitiesAndOptions();
}
