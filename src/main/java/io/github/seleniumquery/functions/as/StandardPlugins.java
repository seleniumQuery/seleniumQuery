package io.github.seleniumquery.functions.as;

import io.github.seleniumquery.SeleniumQueryObject;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * The out-of-the-box plugins provided by seleniumQuery.
 *
 * @author acdcjunior
 * @author ricardo-sc
 *
 * @since 0.9.0
 */
public class StandardPlugins {

    private SeleniumQueryObject seleniumQueryObject;
    private List<WebElement> elements;

    public StandardPlugins(SeleniumQueryObject seleniumQueryObject, List<WebElement> elements) {
        this.seleniumQueryObject = seleniumQueryObject;
        this.elements = elements;
    }

    public AsSelect select() {
        return new AsSelect(seleniumQueryObject, elements);
    }

}