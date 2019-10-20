import org.testng.annotations.BeforeClass;

public class BasePageTest {
    @BeforeClass(alwaysRun = true)
    @org.testng.annotations.Parameters(value = {"config", "environment"})
    public void setUp(String config, String environment) {
        try {
            gixoBasePageFactory = new GixoBasePageFactory("/", config, environment).get();
            desktopHeaderFooter = new GixoDesktopHeaderFooter();
        } catch (IncorrectEnvironmentSetup incorrectEnvironmentSetup) {
            incorrectEnvironmentSetup.printStackTrace();
        }
    }

}
