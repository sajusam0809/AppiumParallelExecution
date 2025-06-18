package org.appium.pageObjects.android;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class HomePage {

    private AndroidDriver driver;

    // Constructor
    public HomePage(AndroidDriver driver) {
        this.driver = driver;
    }

    // Locators
    private By nameField = AppiumBy.id("com.androidsample.generalstore:id/nameField");
    private By radioFemale = AppiumBy.id("com.androidsample.generalstore:id/radioFemale");
    private By radioMale = AppiumBy.id("com.androidsample.generalstore:id/radioMale");
    private By countryDropdown = AppiumBy.id("android:id/text1");
    private By letsShopButton = AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop");

    // Actions
    public void enterName(String name) {
        driver.findElement(nameField).sendKeys(name);
        driver.hideKeyboard();
    }

    public void selectFemaleGender() {
        driver.findElement(radioFemale).click();
    }

    public void selectMaleGender() {
        driver.findElement(radioMale).click();
    }

    public void selectCountry(String countryName) {
        driver.findElement(countryDropdown).click();
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + countryName + "\"));"));
        driver.findElement(By.xpath("//android.widget.TextView[@text='" + countryName + "']")).click();
    }

    public void clickLetsShop() {
        driver.findElement(letsShopButton).click();
    }
}
