package io.github.seleniumquery.internal.fluentfunctions.getters;

import java.util.List;

import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.seleniumquery.SeleniumQueryObject;

public interface Getter<T> {

	default T get(SeleniumQueryObject seleniumQueryObject) {
	    return this.get(seleniumQueryObject.getWebDriver(), seleniumQueryObject.get());
    }

    @Deprecated
	default T get(WebDriver driver, List<WebElement> elements) {
	    throw new NotImplementedException("Dont use this. Temporary.");
    }

}
