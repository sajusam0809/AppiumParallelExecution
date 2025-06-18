package org.appium.pageObjects.android;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.appium.utilis.DriverActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    private AndroidDriver driver;
    private DriverActions actions;

    // Constructor
    public HomePage(AndroidDriver driver) {
        this.driver = driver;
        this.actions = new DriverActions(driver);
        PageFactory.initElements(driver, this);
    }

    // Locators
    private By nameField = AppiumBy.id("com.androidsample.generalstore:id/nameField");
    private By radioFemale = AppiumBy.id("com.androidsample.generalstore:id/radioFemale");
    private By radioMale = AppiumBy.id("com.androidsample.generalstore:id/radioMale");
    private By countryDropdown = AppiumBy.id("android:id/text1");
    private By letsShopButton = AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop");

    // Actions
    public void enterName(String name) {
        WebElement field = driver.findElement(nameField);
        actions.sendKeys(field, name, "Name field");
        driver.hideKeyboard();
    }

    public void selectFemaleGender() {
        actions.click(driver.findElement(radioFemale), "Female radio button");
    }

    public void selectMaleGender() {
        actions.click(driver.findElement(radioMale), "Male radio button");
    }

    public void selectCountry(String countryName) {
        actions.click(driver.findElement(countryDropdown), "Country dropdown");

        // Scroll to country
        String uiSelector = "new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + countryName + "\"));";
        driver.findElement(AppiumBy.androidUIAutomator(uiSelector));

        WebElement countryElement = driver.findElement(By.xpath("//android.widget.TextView[@text='" + countryName + "']"));
        actions.click(countryElement, "Country: " + countryName);
    }

    public void clickLetsShop() {
        actions.click(driver.findElement(letsShopButton), "Let's Shop button");
    }

    public String getToastMessage(String expectedText) {
        // Toast is not a WebElement, but visible via XPath
        String toastXPath = "//android.widget.Toast[@text='" + expectedText + "']";
        String message = driver.findElement(AppiumBy.xpath(toastXPath)).getText();
        actions.captureStep("Toast appeared with message: " + message);
        return message;
    }
}
