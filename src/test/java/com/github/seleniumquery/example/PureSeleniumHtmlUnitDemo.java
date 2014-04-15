package org.openqa.selenium.seleniumquery.example;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class PureSeleniumHtmlUnitDemo {
	
	public static void main(String[] args) {
        WebDriver driver = new HtmlUnitDriver();
        driver.get(new PureSeleniumHtmlUnitDemo().getClass().getClassLoader().getResource("BasicPage.html").toString());
        
        printAll(driver.findElements(By.cssSelector("*")));
        printAll(driver.findElements(By.id("heroSelector")));
		List<WebElement> els = driver.findElements(By.cssSelector("option"));
        
        
        System.out.println("found "+els.size());
		for (WebElement webElement : els) {
			System.out.println(webElement.getTagName() + " -- " + webElement.getAttribute("class"));
			System.out.println("-->####");
			System.out.println(webElement.getClass());

			printAll(webElement.findElements(By.cssSelector("*")));
			System.out.println("<--####");
		}
        
        driver.quit();
	}

	private static void printAll(List<WebElement> els) {
		System.out.println("found "+els.size());
        for (WebElement webElement : els) {
			System.out.println(webElement.getTagName() + " -- " + webElement.getAttribute("class"));
		}
	}

}