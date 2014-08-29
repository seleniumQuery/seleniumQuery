package io.github.seleniumquery.selectorcss;

import io.github.seleniumquery.selector.parser.ParsedSelector;
import io.github.seleniumquery.selector.parser.SelectorParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.w3c.css.sac.Selector;
import org.w3c.css.sac.SelectorList;

public class CssSelectorCompilerService {
	
	public static CompiledCssSelectorList compileSelectorList(WebDriver driver, String selector) {
        try {
    		ParsedSelector<SelectorList> parsedSelector = SelectorParser.parseSelector(selector);
    		SelectorList selectorList = parsedSelector.getSelector();

        	List<CompiledCssSelector> css = new ArrayList<CompiledCssSelector>(selectorList.getLength()); 
        	for (int i = 0; i < selectorList.getLength(); i++) {
        		CompiledCssSelector cs = compileSelector(driver, parsedSelector.getStringMap(), selectorList.item(i));
        		css.add(cs);
        	}
        	
        	return new CompiledCssSelectorList(css);
        	
        } catch (Exception e) {
        	throw new RuntimeException(e);
        }
	}
    
	public static CompiledCssSelector compileSelector(WebDriver driver, Map<String, String> stringMap, Selector selector) {
		@SuppressWarnings("unchecked")
		CssSelector<Selector> cssSelector =  (CssSelector<Selector>) CssSelectorFactory.getInstance().getSelector(selector);
		return cssSelector.compile(driver, stringMap, selector);
	}
	
}