package io.github.seleniumquery.example;

import io.github.seleniumquery.SeleniumQuery;
import io.github.seleniumquery.functions.HtmlFunction;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PureSeleniumHtmlUnitDemo {
	
	public static void main(String[] args) {
//        WebDriver driver = new HtmlUnitDriver(true);
//		WebDriver driver = new FirefoxDriver();
        WebDriver driver = SeleniumQuery.$.browser.setDefaultDriverAsIE().getDefaultDriver();
        driver.get(new PureSeleniumHtmlUnitDemo().getClass().getClassLoader().getResource("BasicPage.html").toString());
        
//        printAll(driver.findElements(By.cssSelector("*")));
        printAllJUST("//div[count(node()) > 0]", driver);
        printAllJUST("//div[string-length(text()) > 0]", driver);
        
        printAll(driver, driver.findElements(By.xpath("//div")));
        
        
        
        
        driver.quit();
	}

	private static void printAll(WebDriver driver, List<WebElement> els) {
		System.out.println("found "+els.size());
        for (WebElement webElement : els) {
			System.out.println(webElement.getTagName() + " --:: `" + HtmlFunction.html(driver, webElement)+"`");
			
			JavascriptExecutor js = (JavascriptExecutor) driver;
			Object o = js.executeScript("return arguments[0].childNodes.length", webElement).toString();
			System.out.println("THERE - .childNodes.length: "+o);
		}
	}
	
	private static void printAllJUST(String xpath ,WebDriver driver) {
		List<WebElement> els = driver.findElements(By.xpath(xpath));
		System.out.println("FOR '"+ xpath +" :: "+els.size());
		for (WebElement webElement : els) {
			System.out.println("\t"+webElement.getAttribute("id"));
		}
	}

}