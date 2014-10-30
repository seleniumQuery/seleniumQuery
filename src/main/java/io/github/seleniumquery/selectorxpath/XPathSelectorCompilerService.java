package io.github.seleniumquery.selectorxpath;

import io.github.seleniumquery.selector.parser.ParsedSelector;
import io.github.seleniumquery.selector.parser.SelectorParser;
import io.github.seleniumquery.selectorcss.CssSelector;
import io.github.seleniumquery.selectorcss.CssSelectorFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.w3c.css.sac.Selector;
import org.w3c.css.sac.SelectorList;

public class XPathSelectorCompilerService {
	
	public static XPathExpressionList compileSelectorList(String selector) {
		ParsedSelector<SelectorList> parsedSelector = SelectorParser.parseSelector(selector);
		SelectorList selectorList = parsedSelector.getSelector();

    	List<XPathExpression> css = new ArrayList<XPathExpression>(selectorList.getLength()); 
    	for (int i = 0; i < selectorList.getLength(); i++) {
    		XPathExpression cs = compileSelector(parsedSelector.getStringMap(), selectorList.item(i));
    		css.add(cs);
    	}
    	
    	return new XPathExpressionList(css);
	}
    
	public static XPathExpression compileSelector(Map<String, String> stringMap, Selector selector) {
		@SuppressWarnings("unchecked")
		CssSelector<Selector> cssSelector =  (CssSelector<Selector>) CssSelectorFactory.getInstance().getSelector(selector);
		return cssSelector.toXPath(stringMap, selector);
	}
	
}