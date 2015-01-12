package io.github.seleniumquery.by.css.pseudoclasses;

import org.w3c.css.sac.SimpleSelector;

import java.util.Map;

public class UnsupportedPseudoClassException extends RuntimeException {
	
	public UnsupportedPseudoClassException(String pseudoClass) {
		super("The pseudo-class \""+pseudoClass+"\" is currently not supported.");
	}

	public UnsupportedPseudoClassException(Map<String, String> stringMap, SimpleSelector selectorUpToThisPoint, String pseudoClassValue) {
		this(new PseudoClassSelector(stringMap, selectorUpToThisPoint, pseudoClassValue).getOriginalPseudoClassSelector());
	}

}