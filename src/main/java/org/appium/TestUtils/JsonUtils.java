package org.appium.TestUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class JsonUtils {

    public static List<Map<String, Object>> readDevicesJson(String jsonPath) {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream is = JsonUtils.class.getResourceAsStream(jsonPath)) {
            return mapper.readValue(is, new TypeReference<List<Map<String, Object>>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to read devices JSON");
        }
    }

    // ✅ New method added without touching existing logic
    public static List<FormData> readFormDataJson(String jsonPath) {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream is = JsonUtils.class.getResourceAsStream(jsonPath)) {
            return mapper.readValue(is, new TypeReference<List<FormData>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to read JSON from " + jsonPath);
        }
    }
}

