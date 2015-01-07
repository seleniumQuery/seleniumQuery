package io.github.seleniumquery.by.filter;

import io.github.seleniumquery.by.xpath.CssCombinationType;

import org.openqa.selenium.WebElement;

public class ElementFilterListCombinator {

	public static ElementFilterList combine(WebElement webElement,
												String xPathExpression,
												ElementFilterList elementFilterList,
													CssCombinationType cssCombinationType,
														String xPathExpression2,
														ElementFilterList elementFilterList2) {
		
		switch (cssCombinationType) {
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