/*
package org.appium.utilis;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.appium.TestUtils.FormData;

import java.io.File;
import java.util.List;

public class TestDataManager {
    public static FormData getFormData(int index) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<FormData> data = mapper.readValue(
                new File(System.getProperty("user.dir") + "/src/main/resources/eCommerce.json"),
                new TypeReference<>() {});
        return data.get(index);
    }
}
*/
