package Core;

import org.apache.commons.io.FileUtils;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;


public class ScreenShotRule extends TestWatcher {
    private WebDriver driver;
    private static String testName;

    public ScreenShotRule(WebDriver driver) {
        this.driver = driver;
    }

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
