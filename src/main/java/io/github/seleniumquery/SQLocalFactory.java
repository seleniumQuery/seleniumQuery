package io.github.seleniumquery;

import java.util.List;

import org.openqa.selenium.WebElement;

public class SeleniumQueryLocalFactory {
	
	private static final SeleniumQueryLocalFactory instance = new SeleniumQueryLocalFactory();
	
	public static final SeleniumQueryLocalFactory getInstance() {
		return instance;
	}
	
	private SeleniumQueryLocalFactory() { }
	
	public SeleniumQueryObject create(SeleniumQueryObject seleniumQueryObject, List<WebElement> elements) {
		return new SeleniumQueryObject(seleniumQueryObject.getWebDriver(), elements, seleniumQueryObject);
	}

}