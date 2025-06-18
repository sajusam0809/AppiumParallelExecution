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
        if (path != null) {
            ExtentReportManager.getTest()
                    .log(Status.PASS, "✅ Test Passed")
                    .addScreenCaptureFromPath(path);
        } else {
            ExtentReportManager.getTest().log(Status.PASS, "✅ Test Passed (screenshot not available)");
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        failedCount++;
        String path = captureScreenshot(result);
        if (path != null) {
            ExtentReportManager.getTest()
                    .log(Status.FAIL, "❌ Test Failed: " + result.getThrowable())
                    .addScreenCaptureFromPath(path);
        } else {
            ExtentReportManager.getTest().log(Status.FAIL, "❌ Test Failed (screenshot not available): " + result.getThrowable());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        skippedCount++;
        String path = captureScreenshot(result);
        if (path != null) {
            ExtentReportManager.getTest()
                    .log(Status.SKIP, "⚠️ Test Skipped")
                    .addScreenCaptureFromPath(path);
        } else {
            ExtentReportManager.getTest().log(Status.SKIP, "⚠️ Test Skipped (screenshot not available)");
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        int total = passedCount + failedCount + skippedCount;

        System.out.println("\n📊 Test Summary:");
        System.out.println("✅ Passed:  " + passedCount);
        System.out.println("❌ Failed:  " + failedCount);
        System.out.println("⚠️ Skipped: " + skippedCount);
        System.out.println("Σ  Total:   " + total);

        ExtentReportManager.getExtent()
                .createTest("📊 Test Summary")
                .info("✅  Passed:  " + passedCount)
                .info("❌  Failed:  " + failedCount)
                .info("⚠️  Skipped: " + skippedCount)
                .info("Σ    Total:   " + total);

        ExtentReportManager.flush();
        System.out.println("📝 Test Suite Finished: Report generated.");
    }

    private String captureScreenshot(ITestResult result) {
        try {
            AndroidDriver driver = ((BaseTest) result.getInstance()).driver;
            if (driver == null) return null;

            String methodName = result.getMethod().getMethodName();
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String screenshotDir = "test-output/media/";
            File destFile = new File(screenshotDir + methodName + "_" + timestamp + ".png");

            destFile.getParentFile().mkdirs();
            byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            try (FileOutputStream fos = new FileOutputStream(destFile)) {
                fos.write(screenshotBytes);
            }

            // Return relative path for ExtentReport embedding
            return "media/" + destFile.getName();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
