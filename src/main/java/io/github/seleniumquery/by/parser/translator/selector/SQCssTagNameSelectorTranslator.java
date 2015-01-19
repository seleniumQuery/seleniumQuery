package io.github.seleniumquery.by.parser.translator.selector;

import io.github.seleniumquery.by.csstree.selector.SQCssTagNameSelector;
import org.w3c.css.sac.ElementSelector;

/**
 * $("tagname")
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class SQCssTagNameSelectorTranslator {

	public SQCssTagNameSelector translate(ElementSelector elementSelector) {
		String tagName = elementSelector.toString();
		return new SQCssTagNameSelector(tagName);
	}

}