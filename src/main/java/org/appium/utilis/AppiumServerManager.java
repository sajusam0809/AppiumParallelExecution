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


        /*File nodeExecutable = new File("C://Program Files//nodejs//node.exe");
        // ✅ Full path to Appium main.js
        File appiumMainJs = new File("C://Users//JenkinsUser//AppData//Roaming//npm//node_modules//appium//build//lib//main.js");

        AppiumServiceBuilder builder = new AppiumServiceBuilder()
                .usingDriverExecutable(nodeExecutable)
                .withAppiumJS(appiumMainJs)
                .usingPort(port);

        service = AppiumDriverLocalService.buildService(builder);
        service.start();*/
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
