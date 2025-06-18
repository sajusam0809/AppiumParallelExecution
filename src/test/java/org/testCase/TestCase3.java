package org.testCase;

import org.appium.TestUtils.BaseTest;
import org.appium.pageObjects.android.HomePage;
import org.testng.annotations.Test;

public class TestCase3 extends BaseTest {

    @Test
    public void ThirdCase() throws InterruptedException {

        HomePage homePage = new HomePage(driver);
        Thread.sleep(5000);
        homePage.enterName(formData.name);
        if ("Female".equalsIgnoreCase(formData.gender)) {
            homePage.selectFemaleGender();
        } else {
            homePage.selectMaleGender();
        }
        homePage.selectCountry(formData.country);
        homePage.clickLetsShop();

        Thread.sleep(3000);
    }
}
