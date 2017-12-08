package io.github.seleniumquery.internal.fluentfunctions.getters;

import io.github.seleniumquery.SeleniumQueryObject;

public class SizeGetter implements Getter<Integer> {

	public static SizeGetter SIZE_GETTER = new SizeGetter();

	private SizeGetter() { }

    @Override
    public Integer get(SeleniumQueryObject seleniumQueryObject) {
		return seleniumQueryObject.size();
	}

	@Override
	public String toString() {
		return "size()";
	}

}
