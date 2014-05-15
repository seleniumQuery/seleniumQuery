package io.github.seleniumquery.by.evaluator.conditionals.pseudoclasses;

/**
 * http://api.jquery.com/image-selector/
 * 
 * @since 1.0.0
 * @author acdcjunior
 */
public class ImagePseudoClass {
	
	private static final PseudoClass instance = new InputTypeAttributePseudoClass("image");
	public static PseudoClass getInstance() {
		return instance;
	}
	private ImagePseudoClass() { }
	
}