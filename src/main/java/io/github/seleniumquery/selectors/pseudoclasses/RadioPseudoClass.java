package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

/**
 * http://api.jquery.com/radio-selector/
 * 
 * @since 1.0.0
 * @author acdcjunior
 */
public class RadioPseudoClass {
	
	private static final PseudoClass instance = new InputTypeAttributePseudoClass("radio");
	public static PseudoClass getInstance() {
		return instance;
	}
	private RadioPseudoClass() { }
	
}