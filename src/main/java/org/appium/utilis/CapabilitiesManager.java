package org.appium.utilis;

import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

    public class CapabilitiesManager {

        public static DesiredCapabilities getCapabilities(Map<String, Object> device) {
            DesiredCapabilities caps = new DesiredCapabilities();

            caps.setCapability(MobileCapabilityType.DEVICE_NAME, device.get("deviceName"));
            caps.setCapability(MobileCapabilityType.UDID, device.get("udid"));
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, device.get("platformName"));
            caps.setCapability("automationName", "UiAutomator2");
            caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, device.get("platformVersion"));
            caps.setCapability("systemPort", device.get("systemPort"));
            caps.setCapability("appPackage", device.get("appPackage"));
            caps.setCapability("appActivity", device.get("appActivity"));
            caps.setCapability(MobileCapabilityType.NO_RESET, true);

            // ✅ Important: Use systemPort for parallel runs
            if (device.containsKey("systemPort")) {
                caps.setCapability("systemPort", device.get("systemPort"));
            }

            return caps;
        }
    }

