package io.github.seleniumquery.by.parser;

import io.github.seleniumquery.by.csstree.SQCssSelectorList;
import io.github.seleniumquery.by.csstree.selector.SQCssSelector;
import io.github.seleniumquery.by.parser.translator.selector.SQCssSelectorTranslator;
import io.github.seleniumquery.by.preparser.CSSParsedSelectorList;
import io.github.seleniumquery.by.preparser.CSSSelectorParser;
import org.w3c.css.sac.Selector;
import org.w3c.css.sac.SelectorList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SQParseTreeBuilder {

	private static SQCssSelectorTranslator sqCssSelectorTranslator = new SQCssSelectorTranslator();
	
	public static SQCssSelectorList parse(String selector) {
		CSSParsedSelectorList cssParsedSelectorList = CSSSelectorParser.parseSelector(selector);
		SelectorList selectorList = cssParsedSelectorList.getSelectorList();

    	List<SQCssSelector> css = new ArrayList<SQCssSelector>(selectorList.getLength());
    	for (int i = 0; i < selectorList.getLength(); i++) {
			SQCssSelector cs = translate(cssParsedSelectorList.getStringMap(), selectorList.item(i));
    		css.add(cs);
    	}
    	
    	return new SQCssSelectorList(css);
	}
    
	public static SQCssSelector translate(Map<String, String> stringMap, Selector selector) {
		return sqCssSelectorTranslator.translate(stringMap, selector);
	}

}