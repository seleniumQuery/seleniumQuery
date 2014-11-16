package io.github.seleniumquery.by.selector.preparser;

import java.util.Map;

public class TransformedSelector {
	
	private String transformedSelector;
	private Map<String, String> stringMap;

	public TransformedSelector(String transformedSelector, Map<String, String> stringMap) {
		this.transformedSelector = transformedSelector;
		this.stringMap = stringMap;
	}

	public String getTransformedSelector() {
		return this.transformedSelector;
	}

	public Map<String, String> getStringMap() {
		return this.stringMap;
	}

}