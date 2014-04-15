package org.openqa.selenium.seleniumquery.wait.evaluators;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface Evaluator<T> {

	boolean evaluate(WebDriver driver, List<WebElement> elements, T valueArgument);

	String stringFor(T valueArgument);

}