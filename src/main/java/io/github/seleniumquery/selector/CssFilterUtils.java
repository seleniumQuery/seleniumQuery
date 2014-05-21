package io.github.seleniumquery.selector;


import java.util.ArrayList;
import java.util.List;

public class CssFilterUtils {

	public static CompiledCssSelector combine(CompiledCssSelector compiledSelector, CompiledCssSelector compiledCondition) {
		String combinedSelector = compiledSelector.getCssSelector() + compiledCondition.getCssSelector();
		List<CssFilter> filters = new ArrayList<CssFilter>(compiledSelector.getCssFilter().size() + compiledCondition.getCssFilter().size());
		filters.addAll(compiledSelector.getCssFilter());
		filters.addAll(compiledCondition.getCssFilter());
		
		return new CompiledCssSelector(combinedSelector, filters);
	}

}
