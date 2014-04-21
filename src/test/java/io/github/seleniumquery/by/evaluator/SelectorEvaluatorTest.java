package io.github.seleniumquery.by.evaluator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import io.github.seleniumquery.TestInfrastructure;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SelectorEvaluatorTest {
	
	static WebDriver driver;
	
	@BeforeClass
	public static void before() {
		driver = TestInfrastructure.getDriver();
//		driver = new FirefoxDriver();
//    	System.setProperty("webdriver.chrome.driver", "F:\\desenv\\chromedriver.exe");
//    	driver = new ChromeDriver();
//		System.setProperty("webdriver.ie.driver",  "F:\\desenv\\IEDriverServer.exe");
//    	driver = new InternetExplorerDriver();
		driver.get(TestInfrastructure.getHtmlTestFileUrl(SelectorEvaluatorTest.class));
	}
	
	@AfterClass
	public static void after() {
		driver.quit();
	}
	
    // E ~ F
	@Test
	public void general_adjacent() {
		WebElement optionElement = driver.findElement(By.cssSelector(".e4"));
		assertThat(SelectorEvaluator.is(driver, optionElement, "option ~ option"), is(true));
		assertThat(SelectorEvaluator.is(driver, optionElement, "select ~ option"), is(false));
		assertThat(SelectorEvaluator.is(driver, optionElement, "option ~ select"), is(false));
		
		WebElement brazilian_p = driver.findElement(By.id("brazilian-p"));
		assertThat(SelectorEvaluator.is(driver, brazilian_p, "p ~ p"), is(false));
		assertThat(SelectorEvaluator.is(driver, brazilian_p, "body ~ p"), is(false));
		assertThat(SelectorEvaluator.is(driver, brazilian_p, "select ~ p"), is(false));
		assertThat(SelectorEvaluator.is(driver, brazilian_p, "p ~ select"), is(false));
		
		WebElement french_p = driver.findElement(By.id("french-p"));
		assertThat(SelectorEvaluator.is(driver, french_p, "p ~ p"), is(true));
		assertThat(SelectorEvaluator.is(driver, french_p, "body ~ p"), is(false));
		assertThat(SelectorEvaluator.is(driver, french_p, "select ~ p"), is(false));
		assertThat(SelectorEvaluator.is(driver, french_p, "p ~ select"), is(false));
		
		WebElement hero_combo = driver.findElement(By.id("hero-combo"));
		assertThat(SelectorEvaluator.is(driver, hero_combo, "p ~ select"), is(true));
		assertThat(SelectorEvaluator.is(driver, hero_combo, "select ~ option"), is(false));
		
		WebElement containsDiv = driver.findElement(By.id("containsDiv"));
		assertThat(SelectorEvaluator.is(driver, containsDiv, "p ~ div"), is(true));
		assertThat(SelectorEvaluator.is(driver, containsDiv, "select ~ div"), is(true));
		assertThat(SelectorEvaluator.is(driver, containsDiv, "div ~ div"), is(true));
		assertThat(SelectorEvaluator.is(driver, containsDiv, "div ~ *"), is(true));
		assertThat(SelectorEvaluator.is(driver, containsDiv, "p ~ p ~ *"), is(true));
		assertThat(SelectorEvaluator.is(driver, containsDiv, "p ~ p ~ div"), is(true));
	}

}