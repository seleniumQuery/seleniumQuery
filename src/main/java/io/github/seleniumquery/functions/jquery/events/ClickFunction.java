package io.github.seleniumquery.functions.jquery.events;

import io.github.seleniumquery.SeleniumQueryException;
import io.github.seleniumquery.SeleniumQueryObject;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ClickFunction {

    public static SeleniumQueryObject click(SeleniumQueryObject seleniumQueryObject, List<WebElement> elements) {
        for (WebElement element : elements) {
            try {
                element.click();
            } catch (ElementNotVisibleException e) {
                throw new SeleniumQueryException("The matched set from " + seleniumQueryObject + " contains at least one element " +
                        "that is not visible and, as such, nor a user, nor seleniumQuery may interact with it.\n Invisible element: " + toString(element), e);
            }
        }
        return seleniumQueryObject;
    }

    private static String toString(WebElement element) {
        return "\nid attribute: \"" + element.getAttribute("id") +
                "\"\nclass attribute: \"" + element.getAttribute("class") +
                "\"\ntag: \"" + element.getTagName() +
                "\"\ntext: \"" + element.getText() +
                "\"\nvalue attribute: " + element.getAttribute("value") +
                "\"\nsize()/dimension: " + element.getSize() +
                "\nisDisplayed(): " + element.isDisplayed() +
                "\nisEnabled(): " + element.isEnabled() +
                "\ntoString(): " + element + "\n";
    }

}