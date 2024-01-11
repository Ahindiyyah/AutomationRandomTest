package AutomationStore;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AddingTheOddItems {
	WebDriver driver = new ChromeDriver();
	String WebSite = "https://automationteststore.com/";
	Random rand = new Random();

	@BeforeTest
	public void SetUp() {
		driver.get(WebSite);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

	}

	@Test()
	public void TheTest() {
		WebElement HairProduct = driver.findElement(By.xpath("//*[@id=\"categorymenu\"]/nav/ul/li[7]/a"));
		HairProduct.click();
		WebElement Conditioner = driver
				.findElement(By.xpath("//*[@id=\"maincontainer\"]/div/div/div/div/ul/li[1]/div/a"));
		Conditioner.click();
		List<WebElement> TheConditionerProduct = driver.findElements(By.xpath(
				"//*[@id=\"maincontainer\"]/div/div/div/div/div[2]/div[@class=\"col-md-3 col-sm-6 col-xs-12\"]"));
		System.out.println(TheConditionerProduct.size());
		for (int i = 0; i < TheConditionerProduct.size(); i += 2) {
			WebElement TheProduct = TheConditionerProduct.get(i);
			TheProduct.click();
			if (i == 4) {
				TheProduct.click();
			}

			WebElement AddToCart = driver.findElement(By.xpath("//ul[@class=\"productpagecart\"]/li/a"));
			AddToCart.click();
			for (int x = 0; x < 2; x++) {
				driver.navigate().back();
			}
			TheConditionerProduct = driver.findElements(By.xpath(
					"//*[@id=\"maincontainer\"]/div/div/div/div/div[2]/div[@class=\"col-md-3 col-sm-6 col-xs-12\"]"));
		}
		WebElement TheSumPrices = driver
				.findElement(By.xpath("/html/body/div/header/div[2]/div/div[3]/ul/li/a/span[2]"));
		String TheSumPricePrinted = TheSumPrices.getText();
		System.out.println(TheSumPricePrinted);
	}

	@AfterTest
	public void Final() {
		driver.close();
	}

}
