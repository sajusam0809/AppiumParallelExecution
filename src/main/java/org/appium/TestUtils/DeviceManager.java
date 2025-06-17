package org.appium.TestUtils;

import java.util.List;

public class DeviceManager {

    public static List<Device> getDevices() {
        return JsonUtils.readDevicesJson("/devices.json");
    }
}

class Device {
    public String deviceName;
    public String udid;
    public String platformName;
    public String platformVersion;
    public String appPackage;
    public String appActivity;
    public int appiumPort;

    // getters and setters (optional)
}
