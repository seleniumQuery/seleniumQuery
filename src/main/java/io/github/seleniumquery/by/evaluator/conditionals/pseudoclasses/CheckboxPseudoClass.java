package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

/**
 * http://api.jquery.com/checkbox-selector/
 * 
 * @since 1.0.0
 * @author acdcjunior
 */
public class CheckboxPseudoClass {
	
	private static final PseudoClass instance = new InputTypeAttributePseudoClass("checkbox");
	public static PseudoClass getInstance() {
		return instance;
	}
	private CheckboxPseudoClass() { }
	
}