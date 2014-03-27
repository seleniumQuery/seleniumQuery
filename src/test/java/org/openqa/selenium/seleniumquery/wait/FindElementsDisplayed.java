package org.openqa.selenium.seleniumquery.wait;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.htmlunit.HtmlUnitWebElement;

import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlElement;

public class FindElementsDisplayed {
    public static void main(String[] args) {
        WebDriver driver = new HtmlUnitDriver();
//        WebDriver driver = new FirefoxDriver();
        driver.get("http://fiddle.jshell.net/acdcjunior/8PvgA/2/show/");
//        WebElement element = driver.findElement(By.name("q"));
        
        {
	        List<WebElement> elementOption = driver.findElements(By.xpath("//div"));
	        
	        for (WebElement webElement : elementOption) {
				System.out.println("element: "+webElement);
				System.out.println("getText(): "+webElement.getText());
				System.out.println("isDisplayed(): "+ webElement.isDisplayed());
				System.out.println("isDisplayed(): "+ webElement.getClass());
				
			}
	    }
        
        try {
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO: handle exception
		}
        
        {
            List<WebElement> elementOption = driver.findElements(By.xpath("//input"));
            System.err.println("SIZE: " + elementOption.size());
            
            for (WebElement webElement : elementOption) {
    			System.err.println("element: "+webElement);
    			System.err.println("getText(): "+webElement.getText());
    			System.err.println("isDisplayed(): "+ webElement.isDisplayed());
    			System.err.println("isDisplayed(): "+ webElement.getClass());
    			
    		}
        }

        // Enter something to search for
//        element.sendKeys("Cheese!");

        // Now submit the form. WebDriver will find the form for us from the element
//        element.submit();

        // Check the title of the page
        System.out.println("Page title is: " + driver.getTitle());
    }
}