package io.github.seleniumquery.selector;

import io.github.seleniumquery.selector.parser.ParsedSelector;
import io.github.seleniumquery.selector.parser.SelectorParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.w3c.css.sac.Selector;
import org.w3c.css.sac.SelectorList;

public class XPathSelectorCompilerService {
	
	public static XPathCompiledSelectorList compileSelectorList(WebDriver driver, String selector) {
        try {
    		ParsedSelector<SelectorList> parsedSelector = SelectorParser.parseSelector(selector);
    		SelectorList selectorList = parsedSelector.getSelector();

        	List<SqXPathSelector> css = new ArrayList<SqXPathSelector>(selectorList.getLength()); 
        	for (int i = 0; i < selectorList.getLength(); i++) {
        		SqXPathSelector cs = compileSelector(driver, parsedSelector.getStringMap(), selectorList.item(i));
        		css.add(cs);
        	}
        	
        	return new XPathCompiledSelectorList(css);
        	
        } catch (Exception e) {
        	throw new RuntimeException(e);
        }
	}
    
	public static SqXPathSelector compileSelector(WebDriver driver, Map<String, String> stringMap, Selector selector) {
		@SuppressWarnings("unchecked")
		CssSelector<Selector> cssSelector =  (CssSelector<Selector>) CssSelectorFactory.getInstance().getSelector(selector);
		return cssSelector.toXPath(driver, stringMap, selector);
	}
	
}