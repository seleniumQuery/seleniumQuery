package io.github.seleniumquery.by.css.pseudoclasses;

public class UnsupportedXPathPseudoClassException extends UnsupportedPseudoClassException {
	
	private static final long serialVersionUID = 1L;
	
	public static boolean xPathFiltersAreNotImplementedYed(String pseudoClass) {
		throw new UnsupportedXPathPseudoClassException(pseudoClass);
	}

	public UnsupportedXPathPseudoClassException(String pseudoClass) {
		super(pseudoClass);
	}

}