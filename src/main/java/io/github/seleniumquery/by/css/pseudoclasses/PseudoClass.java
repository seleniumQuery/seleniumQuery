package io.github.seleniumquery.by.css.pseudoclasses;

import io.github.seleniumquery.by.xpath.component.XPathComponent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface PseudoClass {

	boolean isApplicable(String pseudoClassValue);

	boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector);

	XPathComponent pseudoClassToXPath(PseudoClassSelector pseudoClassSelector);

}