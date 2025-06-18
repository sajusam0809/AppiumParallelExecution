package org.testCase;

import org.appium.TestUtils.BaseTest;
import org.appium.pageObjects.android.HomePage;
import org.testng.annotations.Test;

public class TestCase3 extends BaseTest {

    @Test
    public void SecondCase() throws InterruptedException {
        System.out.println("Device: " + device.get("deviceName"));

        HomePage homePage = new HomePage(driver);

        Thread.sleep(5000);
        homePage.enterName("Steve Smith");
        homePage.selectFemaleGender();
        homePage.selectCountry("Argentina");
        homePage.clickLetsShop();

        Thread.sleep(3000);
    }
}
