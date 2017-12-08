package io.github.seleniumquery.internal.fluentfunctions.getters;

import io.github.seleniumquery.SeleniumQueryObject;

public interface Getter<T> {

	T get(SeleniumQueryObject seleniumQueryObject);

}
