package org.testCase;

import io.appium.java_client.AppiumBy;
import org.appium.TestUtils.BaseTest;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class TestCase2 extends BaseTest {

    @Test
    public void SecondCase() throws InterruptedException {
        //System.out.println("Device: " + device.get("deviceName"));
        // Add your test logic
        Thread.sleep(5000);
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).sendKeys("Steve Smith");
        driver.hideKeyboard();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/radioFemale")).click();
        driver.findElement(AppiumBy.id("android:id/text1")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
        driver.findElement(By.xpath("//android.widget.TextView[@text='Argentina']")).click();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        Thread.sleep(3000);


    }
}
