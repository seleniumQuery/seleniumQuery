package io.github.seleniumquery.selectorcss;


import io.github.seleniumquery.locator.ElementFilter;

import java.util.ArrayList;
import java.util.List;

public class CssFilterUtils {

	public static CompiledCssSelector combine(CompiledCssSelector compiledSelector, CompiledCssSelector compiledCondition) {
		String combinedSelector = compiledSelector.getCssSelector() + compiledCondition.getCssSelector();
		List<ElementFilter> filters = new ArrayList<ElementFilter>(compiledSelector.getCssFilter().size() + compiledCondition.getCssFilter().size());
		filters.addAll(compiledSelector.getCssFilter());
		filters.addAll(compiledCondition.getCssFilter());
		
		return new CompiledCssSelector(combinedSelector, filters);
	}

}
