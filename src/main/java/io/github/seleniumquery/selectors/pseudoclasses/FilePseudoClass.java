package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

/**
 * http://api.jquery.com/file-selector/
 * 
 * @since 1.0.0
 * @author acdcjunior
 */
public class FilePseudoClass {
	
	private static final PseudoClass instance = new InputTypeAttributePseudoClass("file");
	public static PseudoClass getInstance() {
		return instance;
	}
	private FilePseudoClass() { }
	
}