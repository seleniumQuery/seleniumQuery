package io.github.seleniumquery.functions.jquery.forms;

import io.github.seleniumquery.SeleniumQueryObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

/**
 * <pre>
 * $("selector").submit();
 * </pre>
 *
 * @author acdcjunior
 *
 * @since 0.9.0
 */
public class SubmitFunction {

    private static final Log LOGGER = LogFactory.getLog(SubmitFunction.class);

    public static void submit(SeleniumQueryObject seleniumQueryObject) {
        for (WebElement webElement : seleniumQueryObject) {
            try {
                webElement.submit();
            } catch (StaleElementReferenceException e) {
                LOGGER.warn(".submit() was called on an element that is not present anymore. Ignoring further elements on the matched set. (Maybe the .submit() execution on previous elements changed the page.)");
                LOGGER.debug(".submit() exception follows.", e);
                return;
            }
        }
    }

}