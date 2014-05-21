package io.github.seleniumquery.by.selector;

import io.github.seleniumquery.by.evaluator.CSSSelector;
import io.github.seleniumquery.by.evaluator.SelectorEvaluatorFactory;
import io.github.seleniumquery.by.parser.ParsedSelector;
import io.github.seleniumquery.by.parser.SelectorParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.w3c.css.sac.Selector;
import org.w3c.css.sac.SelectorList;

public class SeleniumQueryCssCompiler {
	
	public static CompiledSelectorList compileSelectorList(WebDriver driver, String selector) {
        try {
    		ParsedSelector<SelectorList> parsedSelector = SelectorParser.parseSelector(selector);
    		SelectorList selectorList = parsedSelector.getSelector();

        	List<CompiledSelector> css = new ArrayList<CompiledSelector>(selectorList.getLength()); 
        	for (int i = 0; i < selectorList.getLength(); i++) {
        		CompiledSelector cs = compileSelector(driver, parsedSelector.getStringMap(), selectorList.item(i));
        		css.add(cs);
        	}
        	
        	return new CompiledSelectorList(css);
        	
        } catch (Exception e) {
        	throw new RuntimeException(e);
        }
	}
    
	public static CompiledSelector compileSelector(WebDriver driver, Map<String, String> stringMap, Selector selector) {
		@SuppressWarnings("unchecked")
		CSSSelector<Selector> cssSelector =  (CSSSelector<Selector>) SelectorEvaluatorFactory.getInstance().getSelector(selector);
		return cssSelector.compile(driver, stringMap, selector);
	}
	
}