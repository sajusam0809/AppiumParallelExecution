package org.testCase;

import io.appium.java_client.AppiumBy;
import org.appium.TestUtils.BaseTest;
import org.appium.pageObjects.android.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCase1 extends BaseTest {

    @Test
    public void FirstCase() throws InterruptedException {
        HomePage homePage = new HomePage(driver);

        // Simple example: just print current activity
        //System.out.println("Device: " + device.get("deviceName"));
        System.out.println("Current Activity: " + driver.currentActivity());
        // Add your real test steps here
        Thread.sleep(5000);
        homePage.clickLetsShop();


        String toastMessage = homePage.getToastMessage("Please enter your name");
        System.out.println("Toast Message: " + toastMessage);
        Assert.assertEquals(toastMessage, "Please enter your name");


    }
}

