package io.github.seleniumquery.wait;

import io.github.seleniumquery.SeleniumQueryConfig;
import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.wait.evaluators.IsEvaluator;
import io.github.seleniumquery.wait.getters.AttrGetter;
import io.github.seleniumquery.wait.getters.HtmlGetter;
import io.github.seleniumquery.wait.getters.PropGetter;
import io.github.seleniumquery.wait.getters.SizeGetter;
import io.github.seleniumquery.wait.getters.TextGetter;
import io.github.seleniumquery.wait.getters.ValGetter;

/**
 * @author acdcjunior
 * @since 0.9.0
 */
public class SeleniumQueryWaitUntil {
	
	private SeleniumQueryObject seleniumQueryObject;
	
	private SeleniumQueryFluentWait fluentWait;
	
	/**
	 * Creates a waitUntil object for the given seleniumQueryObject, with timeout and polling interval
	 * as defined in the config files.
	 * @since 0.9.0
	 */
	public SeleniumQueryWaitUntil(SeleniumQueryObject seleniumQueryObject) {
		this(seleniumQueryObject, SeleniumQueryConfig.getWaitUntilTimeout(), SeleniumQueryConfig.getWaitUntilPollingInterval());
	}
	
	/**
	 * Creates a waitUntil object for the given seleniumQueryObject, with the given timeout and polling interval
	 * as defined in the config files.
	 * @since 0.9.0
	 */
	public SeleniumQueryWaitUntil(SeleniumQueryObject seleniumQueryObject, long waitUntilTimeout) {
		this(seleniumQueryObject, waitUntilTimeout, SeleniumQueryConfig.getWaitUntilPollingInterval());
	}
	
	/**
	 * Creates a waitUntil object for the given seleniumQueryObject, with the given timeout and polling interval.
	 * @since 0.9.0
	 */
	public SeleniumQueryWaitUntil(SeleniumQueryObject seleniumQueryObject, long waitUntilTimeout, long waitUntilPollingInterval) {
		this.seleniumQueryObject = seleniumQueryObject;
		this.fluentWait = new SeleniumQueryFluentWait(waitUntilTimeout, waitUntilPollingInterval);
	}
	
	/**
	 * Requeries the DOM <strong>until at least one element returned</strong> by a query to the selector used
	 * to construct this seleniumQuery object <strong>is matched by the selector given</strong>.
	 * 
	 * @since 0.9.0
	 */
	public SeleniumQueryAndOrThen is(String selector) {
		SeleniumQueryObject seleniumQueryObjectAfterWait = this.fluentWait.waitUntil(IsEvaluator.IS_EVALUATOR, selector, seleniumQueryObject, false);
		return new SeleniumQueryAndOrThen(seleniumQueryObjectAfterWait);
	}
	
	public SeleniumQueryEvaluateUntil<String> val() {
		return new SeleniumQueryEvaluateUntil<String>(this.fluentWait, ValGetter.VAL_GETTER, seleniumQueryObject);
	}
	
	public SeleniumQueryEvaluateUntil<String> text() {
		return new SeleniumQueryEvaluateUntil<String>(this.fluentWait, TextGetter.TEXT_GETTER, seleniumQueryObject);
	}
	
	public SeleniumQueryEvaluateUntil<String> attr(String attributeName) {
		return new SeleniumQueryEvaluateUntil<String>(this.fluentWait, new AttrGetter(attributeName), seleniumQueryObject);
	}
	
	public <T> SeleniumQueryEvaluateUntil<T> prop(String propertyName) {
		return new SeleniumQueryEvaluateUntil<T>(this.fluentWait, new PropGetter<T>(propertyName), seleniumQueryObject);
	}
	
	public SeleniumQueryEvaluateUntil<String> html() {
		return new SeleniumQueryEvaluateUntil<String>(this.fluentWait, HtmlGetter.HTML_GETTER, seleniumQueryObject);
	}
	
	public SeleniumQueryEvaluateUntil<Integer> size() {
		return new SeleniumQueryEvaluateUntil<Integer>(this.fluentWait, SizeGetter.SIZE_GETTER, seleniumQueryObject);
	}
	
}