package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest{
	 String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups= {"Purchase"})
	public void SubmitOrderTest(HashMap<String, String> input) throws IOException 
	{
		
		ProductCatalogue productCatalogue = landingpage.loginApplication(input.get("email"),input.get("password"));
		
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();
		
		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
	    Assert.assertTrue(match);
	    CheckoutPage checkoutPage = cartPage.goToCheckout();
	    checkoutPage.selectCountry("india");
	    ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		
		String text = confirmationPage.getConfirmationMessage();
		//Assert.assertEquals(text, "THANKYOU FOR THE ORDER.");
		Assert.assertTrue(text.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	@Test(dependsOnMethods = {"SubmitOrderTest"})
	public void OrderHistoryTest()
	{
		ProductCatalogue productCatalogue = landingpage.loginApplication("ankit8112@gmail.com", "Ankit@6351");
		OrderPage orderPage = productCatalogue.goToOrderPage();
		
		Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
	}
	
	
	
	// Extent reports
	
	@DataProvider
	public Object[][] getData() throws IOException
	{

		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json"); 
		return new Object[][] {{data.get(0)},{data.get(1)} };
	}
	
//	@DataProvider
//	public Object[][] getData()
//	{
//		return new Object[][] {{"ankit8112@gmail.com","Ankit@6351","ZARA COAT 3"},{"ravi9140@gmail.com","Ravi@6351","ADIDAS ORIGINAL"} };
//	}
//	HashMap<String,String> map = new HashMap<String,String>();
//	map.put("email", "ankit8112@gmail.com");
//	map.put("password", "Ankit@6351");
//	map.put("product", "ZARA COAT 3");
//	HashMap<Object,Object> map1 = new HashMap<Object,Object>();
//	map1.put("email", "ravi9140@gmail.com");
//	map1.put("password", "Ravi@6351");
//	map1.put("product", "ADIDAS ORIGINAL");	

}
