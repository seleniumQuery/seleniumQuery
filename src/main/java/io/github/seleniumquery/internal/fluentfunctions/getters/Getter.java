package io.github.seleniumquery.internal.fluentfunctions.getters;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface Getter<T> {

	T get(WebDriver driver, List<WebElement> elements);

}
