package org.appium.utilis;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.File;

public class AppiumServerManager {
    private AppiumDriverLocalService service;

    public void startServer(int port) {
        service = new AppiumServiceBuilder()
                .usingPort(port)
                .withAppiumJS(new File("C://Users//JenkinsUser//AppData//Roaming//npm//node_modules//appium//build//lib//main.js"))
                .build();
        service.start();
        System.out.println("Appium server started on port: " + port);
    }

    public void stopServer() {
        if (service != null && service.isRunning()) {
            service.stop();
            System.out.println("Appium server stopped");

        }
    }

    public boolean isRunning() {
        return service != null && service.isRunning();
    }
}
