package io.github.seleniumquery.by.selector;

import java.util.ArrayList;
import java.util.List;

public class CSSFilterUtils {

	public static CompiledSelector combine(CompiledSelector compiledSelector, CompiledSelector compiledCondition) {
		String combinedSelector = compiledSelector.getCssSelector() + compiledCondition.getCssSelector();
		List<CSSFilter> filters = new ArrayList<CSSFilter>(compiledSelector.getCssFilter().size() + compiledCondition.getCssFilter().size());
		filters.addAll(compiledSelector.getCssFilter());
		filters.addAll(compiledCondition.getCssFilter());
		
		return new CompiledSelector(combinedSelector, filters);
	}

}
