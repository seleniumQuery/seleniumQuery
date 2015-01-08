package io.github.seleniumquery.by.css.pseudoclasses;

import io.github.seleniumquery.by.xpath.component.ConditionComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface PseudoClass<C extends ConditionComponent> {

	boolean isApplicable(String pseudoClassValue);

	boolean isPseudoClass(WebDriver driver, WebElement element, PseudoClassSelector pseudoClassSelector);

	C pseudoClassToXPath(PseudoClassSelector pseudoClassSelector);

}