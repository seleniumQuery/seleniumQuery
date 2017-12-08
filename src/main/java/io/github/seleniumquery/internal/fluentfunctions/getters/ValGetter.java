package io.github.seleniumquery.internal.fluentfunctions.getters;

import io.github.seleniumquery.SeleniumQueryObject;

public class ValGetter implements Getter<String> {

	public static ValGetter VAL_GETTER = new ValGetter();

	private ValGetter() { }

    @Override
    public String get(SeleniumQueryObject seleniumQueryObject) {
		return seleniumQueryObject.val();
	}

	@Override
	public String toString() {
		return "val()";
	}

}
