package org.openqa.selenium.seleniumquery.by.enhancements;

import java.util.List;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

public interface SeleniumQueryEnhancement {

	public abstract boolean isApplicable(String selector);

	public abstract List<WebElement> apply(String selector, SearchContext context);

}