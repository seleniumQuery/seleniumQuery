package io.github.seleniumquery.by.parser.translator.selector;

import io.github.seleniumquery.by.csstree.selector.SQCssTagNameSelector;
import org.w3c.css.sac.ElementSelector;

import java.util.Map;

/**
 * $("tagname")
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class SQCssTagNameSelectorTranslator {

	public SQCssTagNameSelector translate(Map<String, String> stringMap, ElementSelector selector) {
		String tagName = selector.toString();
		return new SQCssTagNameSelector(tagName);
	}

}