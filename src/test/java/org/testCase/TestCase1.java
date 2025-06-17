package org.testCase;

import io.appium.java_client.AppiumBy;
import org.appium.TestUtils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCase1 extends BaseTest {

    @Test
    public void FirstCase() throws InterruptedException {


        // Simple example: just print current activity
        System.out.println("Device: " + device.get("deviceName"));
        System.out.println("Current Activity: " + driver.currentActivity());
        // Add your real test steps here
        Thread.sleep(5000);
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        String errorMessage= driver.findElement(AppiumBy.xpath("//android.widget.Toast[@text='Please enter your name']")).getText();
        System.out.println(errorMessage);
        Assert.assertEquals(errorMessage,"Please enter your name");


    }
}

