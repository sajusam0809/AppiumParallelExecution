package org.appium.TestUtils;

import io.appium.java_client.android.AndroidDriver;
import org.appium.utilis.AppiumServerManager;
import org.appium.utilis.CapabilitiesManager;
import org.appium.TestUtils.ExtentReportManager;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URL;

public class BaseTest {
    public AndroidDriver driver;
    protected AppiumServerManager appiumServerManager;
    protected Map<String, Object> device;

    @Parameters({"deviceIndex"})
    @BeforeClass(alwaysRun = true)
    public void setUp(@Optional("0") String deviceIndex) throws Exception {
        // Initialize ExtentReports
        ExtentReportManager.initReport();

        // Load devices from JSON
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> devices = mapper.readValue(
                new File(System.getProperty("user.dir") + "/src/main/resources/devices.json"),
                new TypeReference<List<Map<String, Object>>>() {
                });

        int index = Integer.parseInt(deviceIndex);
        device = devices.get(index);

        // Start Appium server
        int port = 4723 + index;
        appiumServerManager = new AppiumServerManager();
        appiumServerManager.startServer(port);

        // Get Capabilities
        DesiredCapabilities caps = CapabilitiesManager.getCapabilities(device);

        // Initialize driver
        driver = new AndroidDriver(new URL("http://127.0.0.1:" + port + "/"), caps);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            Object appPackageObj = device.get("appPackage");
            if (appPackageObj != null) {
                String appPackage = appPackageObj.toString();
                if (driver.isAppInstalled(appPackage)) {
                    driver.terminateApp(appPackage);
                    System.out.println("🛑 App terminated: " + appPackage);
                }
            }
            driver.quit();
        }

        if (appiumServerManager != null) {
            appiumServerManager.stopServer();
        }

        System.out.println("✅ Test completed for device: " + device.get("deviceName"));
    }
}
