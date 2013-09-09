package org.openqa.selenium.seleniumquery;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SQueryHtmlElementList {
	
	private By by;
	private WebDriver driver;
	private List<WebElement> elements;
	private String selector;
	
	public SQueryHtmlElementList(WebDriver driver, String cssSelector) {
		this.driver = driver;
		this.selector = cssSelector;
		this.by = By.cssSelector(this.selector);
		this.elements = this.driver.findElements(by);
	}
	
	public SQueryHtmlElementList(WebDriver driver, WebElement webElement) {
	   this.driver = driver;
	   this.elements = new ArrayList<WebElement>(1);
	   this.elements.add(webElement);
   }

   public int size() {
		return this.elements.size();
	}
   
   public Collection<WebElement> unwrap() {
      return this.elements;
   }
	
	public SQueryHtmlElementList click() {
		for (WebElement element : unwrap()) {
			element.click();
		}
		return this;
	}
	
	public SQueryHtmlElementList not(String cssSelector) {
		SQueryHtmlElementList newList = new SQueryHtmlElementList(this.driver, this.selector);
		List<WebElement> elementsNot = this.driver.findElements(By.cssSelector(cssSelector));
		newList.elements.removeAll(elementsNot);
		return newList;
	}
	
	public WebElement unwrapSingle() {
      if(this.elements.size() != 1) throw new RuntimeException(String.format("Can't resolve a single WebElement (have %s)", this.elements));
      return this.elements.get(0);
   }

   public String text() {
      return first().unwrapSingle().getText();
   }

   public SQueryHtmlElementList first() {
      return new SQueryHtmlElementList(driver,this.elements.get(0));
   }

   public SQueryHtmlElementList val(String value) {
      for(WebElement element : unwrap()) {
         if ("select".equals(element.getTagName())) {
            new Select(element).selectByVisibleText(value);
         } else if ("input".equals(element.getTagName()) && "file".equals(element.getAttribute("type"))) {
            element.sendKeys(value);
         } else {
            element.clear();
            element.sendKeys(value);
         }
      }
      return this;
   }
}
