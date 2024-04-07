package rahulshettyacademy.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";
		
		
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium Webdriver\\chromedriver-win64\\chromedriver.exe");
		//WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client/");
		LandingPage landingpage = new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("ankit8112@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Ankit@6351");
		driver.findElement(By.id("login")).click();
		
		// Explicit wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		// grab all the item in the list & then iterate over list & once the item matches, then click on add to cart.
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		// ng-animating
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		// we need to define explicit wait as the suggestive dropdown takes some time to show result
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
	
		driver.findElement(By.xpath("(//button[contains(@class, 'ta-item')])[2] ")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		// grab the order confirmation text
		
		String text = driver.findElement(By.cssSelector(".hero-primary")).getText();
		//Assert.assertEquals(text, "THANKYOU FOR THE ORDER.");
		Assert.assertTrue(text.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
		
		
		
		
		

	}

}
