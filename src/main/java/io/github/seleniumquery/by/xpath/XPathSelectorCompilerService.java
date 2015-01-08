package io.github.seleniumquery.by.xpath;

import io.github.seleniumquery.by.preparser.ParsedSelector;
import io.github.seleniumquery.by.preparser.SelectorParser;
import io.github.seleniumquery.by.css.CssSelector;
import io.github.seleniumquery.by.css.CssSelectorFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.github.seleniumquery.by.xpath.component.TagComponent;
import io.github.seleniumquery.by.xpath.component.XPathComponent;
import org.w3c.css.sac.Selector;
import org.w3c.css.sac.SelectorList;

public class XPathSelectorCompilerService {
	
	public static XPathExpressionList compileSelectorList(String selector) {
		ParsedSelector<SelectorList> parsedSelector = SelectorParser.parseSelector(selector);
		SelectorList selectorList = parsedSelector.getSelector();

    	List<TagComponent> css = new ArrayList<TagComponent>(selectorList.getLength());
    	for (int i = 0; i < selectorList.getLength(); i++) {
			TagComponent cs = compileSelector(parsedSelector.getStringMap(), selectorList.item(i));
    		css.add(cs);
    	}
    	
    	return new XPathExpressionList(css);
	}
    
	public static TagComponent compileSelector(Map<String, String> stringMap, Selector selector) {
		CssSelector<Selector, TagComponent> cssSelector = CssSelectorFactory.parsedSelectorToCssSelector(selector);
		return cssSelector.toXPath(stringMap, selector);
	}

}