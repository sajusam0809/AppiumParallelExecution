package org.appium.Listeners;

import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import org.appium.TestUtils.BaseTest;
import org.appium.TestUtils.ExtentReportManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.*;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener implements ITestListener, IInvokedMethodListener {

    private int passedCount = 0;
    private int failedCount = 0;
    private int skippedCount = 0;

    @Override
    public void onStart(ITestContext context) {
        ExtentReportManager.initReport();
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName()
                + " - Device" + result.getTestContext().getCurrentXmlTest().getParameter("deviceIndex");
        ExtentReportManager.createTest(testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        passedCount++;
        String path = captureScreenshot(result);
        ExtentReportManager.getTest().log(Status.PASS, "✅ Test Passed")
                .addScreenCaptureFromPath(path);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        failedCount++;
        String path = captureScreenshot(result);
        ExtentReportManager.getTest().log(Status.FAIL, "❌ Test Failed: " + result.getThrowable())
                .addScreenCaptureFromPath(path);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        skippedCount++;
        ExtentReportManager.getTest().log(Status.SKIP, "⚠️ Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        int totalCount = passedCount + failedCount + skippedCount;

        // Print test summary to console
        System.out.println("\n📊 Test Summary:");
        System.out.println("✔  Passed:  " + passedCount);
        System.out.println("✖  Failed:  " + failedCount);
        System.out.println("➖ Skipped: " + skippedCount);
        System.out.println("Σ  Total:   " + totalCount);

        // Log summary in Extent Report
        ExtentReportManager.getExtent()
                .createTest("📊 Test Summary")
                .info("✔  Passed:  " + passedCount)
                .info("✖  Failed:  " + failedCount)
                .info("➖ Skipped: " + skippedCount)
                .info("Σ  Total:   " + totalCount);

        ExtentReportManager.flush();
    }

    private String captureScreenshot(ITestResult result) {
        AndroidDriver driver = ((BaseTest) result.getInstance()).driver;
        String methodName = result.getMethod().getMethodName();
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File screenshotFile = new File("screenshots", methodName + "_" + timestamp + ".png");

        try {
            byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            screenshotFile.getParentFile().mkdirs();
            try (FileOutputStream fos = new FileOutputStream(screenshotFile)) {
                fos.write(screenshotBytes);
            }
            return screenshotFile.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
