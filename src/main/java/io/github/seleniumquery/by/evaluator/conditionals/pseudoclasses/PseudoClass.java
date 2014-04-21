package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.Selector;

public interface PseudoClass {

	boolean isApplicable(String pseudoClassValue);

	boolean isPseudoClass(WebDriver driver, WebElement element, Selector selectorThisConditionShouldApply,
			String pseudoClassValue);

}