package org.appium.TestUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static void initReport() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");
            spark.config().setTheme(Theme.STANDARD);
            spark.config().setDocumentTitle("Appium Test Report");
            spark.config().setReportName("Parallel Execution");


            // Add CSS to resize screenshots globally
            String css = ".test-content img { max-width: 400px; height: auto !important; }";
            spark.config().setCss(css);

            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
    }

    public static void flush() {
        if (extent != null) extent.flush();
    }

    public static void createTest(String testName) {
        ExtentTest extentTest = extent.createTest(testName);
        test.set(extentTest);
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    // Add this method to allow access to the ExtentReports instance
    public static ExtentReports getExtent() {
        return extent;
    }


    public static void logInfoWithScreenshot(AndroidDriver driver, String stepDescription) {
        try {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String screenshotDir = "test-output/media/";
            String fileName = "step_" + timestamp + ".png";
            File destFile = new File(screenshotDir + fileName);
            destFile.getParentFile().mkdirs();

            byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            try (FileOutputStream fos = new FileOutputStream(destFile)) {
                fos.write(screenshotBytes);
            }

            ExtentReportManager.getTest()
                    .log(Status.INFO, stepDescription)
                    .addScreenCaptureFromPath("media/" + fileName);
        } catch (Exception e) {
            ExtentReportManager.getTest().log(Status.WARNING, "⚠️ Failed to log step: " + stepDescription);
        }
    }
}
