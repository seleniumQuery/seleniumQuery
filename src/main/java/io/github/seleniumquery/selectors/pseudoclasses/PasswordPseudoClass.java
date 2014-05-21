package io.github.seleniumquery.selectors.pseudoclasses;

/**
 * http://api.jquery.com/password-selector/
 * 
 * @since 1.0.0
 * @author acdcjunior
 */
public class PasswordPseudoClass {
	
	private static final PseudoClass instance = new InputTypeAttributePseudoClass("password");
	public static PseudoClass getInstance() {
		return instance;
	}
	private PasswordPseudoClass() { }
	
}