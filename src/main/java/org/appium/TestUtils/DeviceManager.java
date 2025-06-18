/*
package org.appium.TestUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.List;
import java.util.Map;

public class DeviceManager {
    public static Map<String, Object> getDevice(int index) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> devices = mapper.readValue(
                new File(System.getProperty("user.dir") + "/src/main/resources/devices.json"),
                new TypeReference<>() {});
        return devices.get(index);
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
*/
