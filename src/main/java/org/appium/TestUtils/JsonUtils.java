package org.appium.TestUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.List;

public class JsonUtils {

    public static List<Device> readDevicesJson(String jsonPath) {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream is = JsonUtils.class.getResourceAsStream(jsonPath)) {
            return mapper.readValue(is, new TypeReference<List<Device>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to read devices JSON");
        }
    }
}
