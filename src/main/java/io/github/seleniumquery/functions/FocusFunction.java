package io.github.seleniumquery.functions;

import io.github.seleniumquery.SeleniumQueryObject;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class FocusFunction {

	public static SeleniumQueryObject focus(SeleniumQueryObject caller, List<WebElement> elements) {
		for (WebElement webElement : elements) {
			focus(caller.getWebDriver(), webElement);
		}
		return caller;
	}

	private static void focus(WebDriver driver, WebElement elementToBeActive) {
		System.out.println("@$ focusing "+print(elementToBeActive));
		System.out.println("@$ current focus is "+printActive(driver));
		
		System.out.println("@! Attempting to move");
		new Actions(driver).moveToElement(elementToBeActive).click().perform();
		System.out.println("@! after move is "+printActive(driver));
		
		elementToBeActive.sendKeys(Keys.TAB);
		System.out.println("@$ sent tab. current focus is "+printActive(driver));

		WebElement activeElement = driver.switchTo().activeElement();
		if (activeElement.equals(elementToBeActive)) {
			System.out.println("@$ wanted is already active. ending");
			return;
		}
		activeElement.sendKeys(Keys.SHIFT, Keys.TAB);
		System.out.println("@$ sent shift+tab. current focus is "+printActive(driver));
//		driver.findElement(By.Id(Id of the button)).sendKeys(Keys.Tab) 
//		return TriggerFunction.trigger(caller, elements, "focus");
	}

	private static String print(WebElement elementToBeActive) {
		return elementToBeActive.getAttribute("id") + "(tag: "+elementToBeActive.getTagName()+")";
	}
	private static String printActive(WebDriver driver) {
		return print(driver.switchTo().activeElement());
	}

}