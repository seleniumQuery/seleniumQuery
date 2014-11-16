package io.github.seleniumquery.selectors.pseudoclasses;

import io.github.seleniumquery.by.selector.xpath.XPathExpression;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface PseudoClass {

	boolean isApplicable(String pseudoClassValue);

	boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector);

	XPathExpression pseudoClassToXPath(PseudoClassSelector pseudoClassSelector);

}