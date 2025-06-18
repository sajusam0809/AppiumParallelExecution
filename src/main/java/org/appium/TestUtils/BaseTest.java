package org.appium.TestUtils;

import io.appium.java_client.android.AndroidDriver;
import org.appium.utilis.AppiumServerManager;
import org.appium.utilis.CapabilitiesManager;
import org.appium.TestUtils.ExtentReportManager;
//import org.appium.TestUtils.DeviceManager;
//import org.appium.utilis.TestDataManager;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.net.URL;
import java.util.List;
import java.util.Map;

public class BaseTest {
    public AndroidDriver driver;
    protected AppiumServerManager appiumServerManager;
    protected Map<String, Object> device;
    protected FormData formData;

    @Parameters({"deviceIndex", "formDataIndex"})
    @BeforeClass(alwaysRun = true)
    public void setUp(@Optional("0") String deviceIndex, @Optional("0") String formDataIndex) throws Exception {
        ExtentReportManager.initReport();

        int dIdx = Integer.parseInt(deviceIndex);
        int fIdx = Integer.parseInt(formDataIndex);

       /* device = DeviceManager.getDevice(dIdx);
        formData = TestDataManager.getFormData(fIdx);*/

        List<Map<String, Object>> devices = JsonUtils.readDevicesJson("/devices.json");
        device = devices.get(dIdx);

        List<FormData> formDataList = JsonUtils.readFormDataJson("/eCommerce.json");
        formData = formDataList.get(fIdx);

        int port = 4723 + dIdx;
        appiumServerManager = new AppiumServerManager();
        appiumServerManager.startServer(port);


        DesiredCapabilities caps = CapabilitiesManager.getCapabilities(device);
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
