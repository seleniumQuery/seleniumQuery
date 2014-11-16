package io.github.seleniumquery.by.selector.xpath;

import io.github.seleniumquery.by.selector.preparser.ParsedSelector;
import io.github.seleniumquery.by.selector.preparser.SelectorParser;
import io.github.seleniumquery.by.selector.css.CssSelector;
import io.github.seleniumquery.by.selector.css.CssSelectorFactory;

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
		CssSelector<Selector> cssSelector = CssSelectorFactory.parsedSelectorToCssSelector(selector);
		return cssSelector.toXPath(stringMap, selector);
	}

	public static XPathExpression compileToDescendantGeneralExpression(Map<String, String> stringMap, Selector cssSelector) {
		return compileAndSetKind(stringMap, cssSelector, CssSelectorType.DESCENDANT_GENERAL);
	}

	public static XPathExpression compileToDescendantDirectExpression(Map<String, String> stringMap, Selector cssSelector) {
		return compileAndSetKind(stringMap, cssSelector, CssSelectorType.DESCENDANT_DIRECT);
	}

	public static XPathExpression compileToAdjacentExpression(Map<String, String> stringMap, Selector cssSelector) {
		return compileAndSetKind(stringMap, cssSelector, CssSelectorType.ADJACENT);
	}

	private static XPathExpression compileAndSetKind(Map<String, String> stringMap, Selector cssSelector, CssSelectorType selectorKind) {
		XPathExpression xPathExpression = compileSelector(stringMap, cssSelector);
		xPathExpression.kind = selectorKind;
		return xPathExpression;
	}

}