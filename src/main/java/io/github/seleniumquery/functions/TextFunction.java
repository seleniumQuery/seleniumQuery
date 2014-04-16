package io.github.seleniumquery.functions;

import java.util.List;

import org.openqa.selenium.WebElement;

public class TextFunction {
	
	public static String text(List<WebElement> elements) {
		if (elements.isEmpty()) {
			return null;
		}
		// Warning!
		// It is impossible to read text from hidden elements in Selenium:
		// Since a user cannot read text in a hidden element, WebDriver will not allow access to it as well.
		// More in WebDriver FAQs: https://code.google.com/p/selenium/wiki/FrequentlyAskedQuestions#Q:_Why_is_it_not_possible_to_interact_with_hidden_elements?
		return elements.get(0).getText();
	}
	
}