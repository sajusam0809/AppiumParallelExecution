package org.appium.utilis;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Allure;
import org.appium.TestUtils.ExtentReportManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import java.io.ByteArrayInputStream;

public class DriverActions {
    private final AndroidDriver driver;

    public DriverActions(AndroidDriver driver) {
        this.driver = driver;
    }

    public void click(WebElement element, String description) {
        element.click();
        logStepWithScreenshot("Clicked: " + description);
    }

    public void sendKeys(WebElement element, String input, String description) {
        element.sendKeys(input);
        logStepWithScreenshot("Typed '" + input + "' into: " + description);
    }

    public String getText(WebElement element, String description) {
        String text = element.getText();
        logStepWithScreenshot("Got text from " + description + ": " + text);
        return text;
    }

    public void captureStep(String message) {
        logStepWithScreenshot(message);
    }

    private void logStepWithScreenshot(String stepDescription) {
        try {
            // Screenshot bytes
            byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            // 🔹 Log to ExtentReports
            ExtentReportManager.logInfoWithScreenshot(driver, stepDescription);

            // 🔹 Log to Allure
            Allure.addAttachment(stepDescription, new ByteArrayInputStream(screenshotBytes));
        } catch (Exception e) {
            // If screenshot fails, log it to Allure
            Allure.addAttachment("❌ Screenshot capture failed", e.getMessage());
            ExtentReportManager.getTest().info("⚠️ Screenshot failed for: " + stepDescription);
        }
    }
}
