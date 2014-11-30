package io.github.seleniumquery.functions.as;

import io.github.seleniumquery.SeleniumQueryObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static io.github.seleniumquery.by.WebElementUtils.isSelectTag;

public class AsSelect {

	private static final Log LOGGER = LogFactory.getLog(AsSelect.class);

	private SeleniumQueryObject caller;

	AsSelect(SeleniumQueryObject caller) {
		this.caller = caller;
	}

	/**
	 * Selects all <code>&lt;option&gt;</code>s that display text matching the argument.
	 * That is, when given <code>"Bar"</code> this would select an option like:
	 *
	 * <code>&lt;option value="foo"&gt;Bar&lt;/option&gt;</code>
	 *
	 * @param text The visible text to match against.
	 * @return A (self) reference to the {@link io.github.seleniumquery.SeleniumQueryObject} this was called on.
	 *
	 * @since 0.9.0
	 */
	public SeleniumQueryObject selectByVisibleText(String text) {
		LOGGER.debug("Selecting <option>s on "+caller+" by visible text: \""+text+"\".");
		for (WebElement element : caller.get()) {
			if (isSelectTag(element)) {
				new Select(element).selectByVisibleText(text);
			}
		}
		return caller;
	}

	/**
	 * Selects all <code>&lt;option&gt;</code>s that have a value matching the argument.
	 * That is, when given <code>"foo"</code> this would select an option like:
	 *
	 * <code>&lt;option value="foo"&gt;Bar&lt;/option&gt;</code>
	 *
	 * @param value The value to match against.
	 * @return A (self) reference to the {@link io.github.seleniumquery.SeleniumQueryObject} this was called on.
	 */
	public SeleniumQueryObject selectByValue(String value) {
		LOGGER.debug("Selecting <option>s on "+caller+" by value: \""+value+"\".");
		for (WebElement element : caller.get()) {
			if (isSelectTag(element)) {
				new Select(element).selectByValue(value);
			}
		}
		return caller;
	}

}