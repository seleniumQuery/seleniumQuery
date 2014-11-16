package io.github.seleniumquery.by.filter;

import io.github.seleniumquery.by.xpath.CssSelectorType;

import org.openqa.selenium.WebElement;

public class ElementFilterListCombinator {

	public static ElementFilterList combine(WebElement webElement,
												String xPathExpression,
												ElementFilterList elementFilterList,
													CssSelectorType cssSelectorType,
														String xPathExpression2,
														ElementFilterList elementFilterList2) {
		
		switch (cssSelectorType) {
			case CONDITIONAL_SIMPLE:
			case CONDITIONAL_TO_ALL:
			case DESCENDANT_GENERAL:
			case DESCENDANT_DIRECT:
			case ADJACENT:
			case TAG:
			default:
				break;
		}
		return elementFilterList;
	}

}