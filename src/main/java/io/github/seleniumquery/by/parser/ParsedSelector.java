package io.github.seleniumquery.by.parser;

import java.util.Map;

public class ParsedSelector<T> {
	
	private T selectorList;
	private Map<String, String> stringMap;

	public ParsedSelector(T selectorList, Map<String, String> stringMap) {
		this.selectorList = selectorList;
		this.stringMap = stringMap;
	}

	public T getSelector() {
		return this.selectorList;
	}

	public Map<String, String> getStringMap() {
		return this.stringMap;
	}
	
}