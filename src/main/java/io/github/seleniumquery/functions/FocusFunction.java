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
//		System.out.println();
//		System.out.println("@$ Begun attempting to .focus() -> "+print(elementToBeActive));
//		System.out.println("@$\t Current focus is -> "+printActive(driver));
//
//		{	
//			System.out.println("@$ First attempt, .moveToElement(elementToFocus)!");
//			new Actions(driver).moveToElement(elementToBeActive).perform();
//			System.out.println("@$\t After MOVE focus is "+printActive(driver));
//			if (driver.switchTo().activeElement().equals(elementToBeActive)) {
//				System.out.println("@$\t After MOVE wanted is already active. Ending: MOVE!");
//				return;
//			}
//		}
//		
//		{
//			System.out.println("@$ Second attempt, .sendKeys(NULL)!");
			elementToBeActive.sendKeys(Keys.NULL);
//			System.out.println("@$\t After .sendKeys(NULL) focus is "+printActive(driver));
//			if (driver.switchTo().activeElement().equals(elementToBeActive)) {
//				System.out.println("@$\t After .sendKeys(NULL) wanted is already active. Ending: .sendKeys(NULL)!");
//				return;
//			}
//		}
//		
//		
//		System.out.println("@$ Third attempt, .sendKeys(TAB)!");
//		elementToBeActive.sendKeys(Keys.TAB);
//		System.out.println("@$ sent tab. current focus is "+printActive(driver));
//
//		WebElement activeElement = driver.switchTo().activeElement();
//		if (activeElement.equals(elementToBeActive)) {
//			System.out.println("@$ wanted is already active. ending");
//			return;
//		}
//		activeElement.sendKeys(Keys.SHIFT, Keys.TAB);
//		System.out.println("@$ sent shift+tab. current focus is "+printActive(driver));
////		driver.findElement(By.Id(Id of the button)).sendKeys(Keys.Tab) 
////		return TriggerFunction.trigger(caller, elements, "focus");
//	}
//
//	private static String print(WebElement elementToBeActive) {
//		return elementToBeActive.getAttribute("id") + "(tag: "+elementToBeActive.getTagName()+")";
//	}
//	private static String printActive(WebDriver driver) {
//		return print(driver.switchTo().activeElement());
	}

}