package io.github.seleniumquery.locator.filter;

import io.github.seleniumquery.selectorxpath.SqSelectorKind;

import org.openqa.selenium.WebElement;

public class ElementFilterListCombinator {

	public static ElementFilterList combine(WebElement webElement,
												String xPathExpression,
												ElementFilterList elementFilterList,
													SqSelectorKind sqSelectorKind,
														String xPathExpression2,
														ElementFilterList elementFilterList2) {
		
		switch (sqSelectorKind) {
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