package io.github.seleniumquery.internal.fluentfunctions.getters;

import io.github.seleniumquery.SeleniumQueryObject;

public class AttrGetter implements Getter<String> {

	private String attributeName;
	public AttrGetter(String attributeName) { this.attributeName = attributeName; }

    @Override
    public String get(SeleniumQueryObject seleniumQueryObject) {
	    return seleniumQueryObject.attr(this.attributeName);
	}

	@Override
	public String toString() {
		return "attr(\""+this.attributeName+"\")";
	}

}
