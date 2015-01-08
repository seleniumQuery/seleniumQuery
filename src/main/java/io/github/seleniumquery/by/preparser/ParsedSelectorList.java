package io.github.seleniumquery.by.preparser;

import org.w3c.css.sac.SelectorList;

import java.util.Collections;
import java.util.Map;

public class ParsedSelectorList {
	
	private final SelectorList selectorList;
	private final Map<String, String> stringMap;

	public ParsedSelectorList(SelectorList selectorList, Map<String, String> stringMap) {
		this.selectorList = selectorList;
		this.stringMap = Collections.unmodifiableMap(stringMap);
	}

	public SelectorList getSelectorList() {
		return this.selectorList;
	}

	public Map<String, String> getStringMap() {
		return this.stringMap;
	}
	
}