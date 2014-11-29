package io.github.seleniumquery.functions.as;

import io.github.seleniumquery.SeleniumQueryObject;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Plugin interface for seleniumQuery.
 *
 * @author acdcjunior
 * @author ricardo-sc
 *
 * @since 0.9.0
 */
public interface SeleniumQueryPlugin<T> {

    T as(SeleniumQueryObject seleniumQueryObject, List<WebElement> elements);

}