package org.openqa.selenium.seleniumquery.wait.restrictor.has;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.seleniumquery.functions.ValFunction;
import org.openqa.selenium.seleniumquery.wait.restrictor.Restrictor;

/**
 * @author acdcjunior
 * @since 0.4.0
 */
public final class HasValEqualTo<T> implements Restrictor {
	
	public static final Restrictor hasValEqualTo(String string) {
		return new HasValEqualTo<String>(string);
	}
	
	public static final Restrictor hasValEqualTo(Number number) {
		return new HasValEqualTo<Number>(number);
	}
	
	private T parameterValue;
	
	private HasValEqualTo(T parameterValue) {
		this.parameterValue = parameterValue;
	}
	
	@Override
	public boolean fulfillsRestriction(WebElement webElement) {
		return ValFunction.val(webElement).equals(this.parameterValue.toString());
	}

	@Override
	public String toString() {
		if (this.parameterValue instanceof Number) {
			return "value equal to "+this.parameterValue;
		}
		return "value equal to \""+this.parameterValue+"\"";
	}

}