package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.Test;
import rahulshettyacademy.TestComponents.Retry;

//import com.sun.net.httpserver.Authenticator.Retry;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest{

	@Test(groups= {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException 
	{
		String productName = "ZARA COAT 3";
		landingpage.loginApplication("ankit8122@gmail.com", "Ankit@6351");
		// landingpage.getErrorMessage();
		Assert.assertEquals("Incorrect email password.", landingpage.getErrorMessage());
	}
	
	@Test
	public void ProductErrorValidation() throws IOException 
	{
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingpage.loginApplication("ankit8112@gmail.com", "Ankit@6351");
		
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		
		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
        Assert.assertFalse(match);

	}

}
