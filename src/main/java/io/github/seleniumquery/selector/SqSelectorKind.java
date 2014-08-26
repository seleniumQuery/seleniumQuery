package io.github.seleniumquery.selector;

public enum SqSelectorKind {
	
	/*
	 * if CONDITIONAL_SIMPLE, then the expr can be just appended to other, such as:
	 * //*[@other][@thisSelector]
	 * 
	 * if CONDITIONAL_TO_ALL, then this must be applied to the whole result of the other, such as:
	 * (//*[@other])[@thisSelector]
	 */
	CONDITIONAL_SIMPLE,
	CONDITIONAL_TO_ALL,
	
	DESCENDANT_GENERAL, // "//"
	DESCENDANT_DIRECT, // "/"
	ADJACENT, // "/following-sibling::"  (the direct will add a position()=1 by itself)
	TAG
	
}