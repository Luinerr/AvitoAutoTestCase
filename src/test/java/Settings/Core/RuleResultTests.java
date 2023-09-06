package Settings.Core;

import org.apache.commons.io.FileUtils;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;


public class RuleResultTests extends TestWatcher {
    private WebDriver driver;
    private static String testName;

    public RuleResultTests(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Перед стартом теста по правилу setName для Rule и BaseSeleniumTest
     * @param description
     */
    @Override
    protected void starting(Description description) {
        RuleResultTests.setName(description.getMethodName());
        BaseSeleniumTest.setNameTest(description.getMethodName());
        System.out.println("Starting test: " + description.getMethodName());
    }

    /**
     * Правило для проваленных тестов
     * Делается скриншот + пишется лог с результатом false
     * analyzeLog(false)
     * @param e
     * @param description
     */
    @Override
    protected void failed(Throwable e, Description description) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File scrFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destFile = getDestinationFile(BaseSeleniumTest.directory);
        try {
            FileUtils.copyFile(scrFile, destFile);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
        BaseSeleniumTest.analyzeLog(false);
    }

    /**
     * Правило для успешных тестов
     * Кидает analyzeLog(true)
     * @param description
     */
    @Override
    protected void succeeded(Description description) {
        BaseSeleniumTest.analyzeLog(true);
    }

    private File getDestinationFile(File directory) {
        String currentDirectory = directory.getAbsolutePath();
        String fileName = BaseSeleniumTest.timeStamp + testName +".png" ;
        String absoluteFileName = currentDirectory + "/" + fileName;
        return new File(absoluteFileName);
    }

    public static void setName(String name) {
        testName = name;
    }
}
