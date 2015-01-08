package io.github.seleniumquery.by.xpath;

import io.github.seleniumquery.by.css.CssSelector;
import io.github.seleniumquery.by.css.CssSelectorFactory;
import io.github.seleniumquery.by.preparser.CSSParsedSelectorList;
import io.github.seleniumquery.by.preparser.CSSSelectorParser;
import io.github.seleniumquery.by.xpath.component.TagComponent;
import org.w3c.css.sac.Selector;
import org.w3c.css.sac.SelectorList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class XPathSelectorCompilerService {
	
	public static TagComponentList compileSelectorList(String selector) {
		CSSParsedSelectorList CSSParsedSelectorList = CSSSelectorParser.parseSelector(selector);
		SelectorList selectorList = CSSParsedSelectorList.getSelectorList();

    	List<TagComponent> css = new ArrayList<TagComponent>(selectorList.getLength());
    	for (int i = 0; i < selectorList.getLength(); i++) {
			TagComponent cs = compileSelector(CSSParsedSelectorList.getStringMap(), selectorList.item(i));
    		css.add(cs);
    	}
    	
    	return new TagComponentList(css);
	}
    
	public static TagComponent compileSelector(Map<String, String> stringMap, Selector selector) {
		CssSelector<Selector, TagComponent> cssSelector = CssSelectorFactory.parsedSelectorToCssSelector(selector);
		return cssSelector.toXPath(stringMap, selector);
	}

}