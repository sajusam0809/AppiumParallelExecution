package org.appium.pageObjects.android;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.appium.utilis.AppiumServerManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class FormPage extends AppiumServerManager {

    AndroidDriver driver;// local object - Access will be in entire class

    public FormPage(AndroidDriver driver) { // send driver as arguments
      // super(driver); // Calling Parents class - driver
        this.driver = driver; // this represents current class instance
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);//initElements - initalize the full locator
    }

    @AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
    private WebElement nameField;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/radioFemale")
    private WebElement radioFemaleOption;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/radioMale")
    private WebElement radioMaleOption;

    @AndroidFindBy(id = "android:id/text1")
    private WebElement countrySelection;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
    private WebElement shopButton;

    public void setNameField(String name) {

        nameField.sendKeys(name);
        driver.hideKeyboard();
    }

    public void SetActivity(){
        Activity activity = new Activity("com.androidsample.generalstore","com.androidsample.generalstore.MainActivity");
        ((JavascriptExecutor)driver).executeScript("mobile:startActivity", ImmutableMap.of("intent","com.androidsample.generalstore/com.androidsample.generalstore.SplashActivity"));
    }

    public void setGender(String gender) {

        if (gender.contains("female"))
            radioFemaleOption.click();
        else radioMaleOption.click();
    }

/*    public void setCountrySelection(String countryName) {
        countrySelection.click();
        scrollToText(countryName);
        driver.findElement(By.xpath("//android.widget.TextView[@text='" + countryName + "']")).click();
    }*/

    public ProductCatalog submitForm() {
        shopButton.click();
        return new ProductCatalog(driver);
    }

}
