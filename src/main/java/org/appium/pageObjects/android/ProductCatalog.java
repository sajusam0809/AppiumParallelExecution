package org.appium.pageObjects.android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.appium.utilis.AppiumServerManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ProductCatalog extends AppiumServerManager {

    AndroidDriver driver;// local object - Access will be in entire class

    public ProductCatalog(AndroidDriver driver) { // send driver as arguments
      // super(driver); // Calling Parents class - driver
        this.driver = driver; // this represents current class instance
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);//initElements - initalize the full locator
    }

    @AndroidFindBy(xpath = "(//android.widget.TextView[@text='ADD TO CART'])[1]")
    private WebElement addToCart;
    @AndroidFindBy(id="com.androidsample.generalstore:id/appbar_btn_cart")
    private WebElement cart;

    public void addItemToCart() {
        addToCart.click();
        addToCart.click();

    }

    public CartPage goToCartPage() throws InterruptedException {
        cart.click();
        Thread.sleep(2000);
        return new CartPage(driver);
    }


}
