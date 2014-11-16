package io.github.seleniumquery.by.css.pseudoclasses;

public class UnsupportedPseudoClassException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public UnsupportedPseudoClassException(String pseudoClass) {
		super("The pseudo-class \""+pseudoClass+"\" is currently not supported.");
	}

}