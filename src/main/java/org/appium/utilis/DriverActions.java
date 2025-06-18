package org.appium.utilis;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.appium.TestUtils.ExtentReportManager;

public class DriverActions {
    private AndroidDriver driver;

    public DriverActions(AndroidDriver driver) {
        this.driver = driver;
    }

    public void click(WebElement element, String description) {
        element.click();
        ExtentReportManager.logInfoWithScreenshot(driver, "Clicked: " + description);
    }

    public void sendKeys(WebElement element, String input, String description) {
        element.sendKeys(input);
        ExtentReportManager.logInfoWithScreenshot(driver, "Typed '" + input + "' into: " + description);
    }

    public String getText(WebElement element, String description) {
        String text = element.getText();
        ExtentReportManager.logInfoWithScreenshot(driver, "Got text from " + description + ": " + text);
        return text;
    }
    public void captureStep(String message) {
        ExtentReportManager.logInfoWithScreenshot(driver, message);
    }
}
