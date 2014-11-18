package io.github.seleniumquery.by;

import org.openqa.selenium.WebElement;

/**
 * Several functions for dealing with {@link WebElement}s.
 *
 * @author acdcjunior
 *
 * @since 0.9.0
 */
public class WebElementUtils {

    public static boolean elementHasAttribute(WebElement element, String attributeName, String attributeValue) {
        return attributeValue.equalsIgnoreCase(element.getAttribute(attributeName));
    }

    public static boolean isTextareaTag(WebElement element) {
        return "textarea".equals(element.getTagName());
    }

    public static boolean isSelectTag(WebElement element) {
        return "select".equals(element.getTagName());
    }

    public static boolean isOptionTag(WebElement element) {
        return "option".equals(element.getTagName());
    }

    public static boolean isInputTag(WebElement element) {
        return "input".equals(element.getTagName());
    }

    public static boolean isInputFileTag(WebElement element) {
        return isInputTagWithType(element, "file");
    }

    private static boolean isInputTagWithType(WebElement element, String file) {
        return isInputTag(element) && elementHasAttribute(element, "type", file);
    }

    public static boolean isInputRadioTag(WebElement element) {
        return isInputTagWithType(element, "radio");
    }

    public static boolean isInputCheckboxTag(WebElement element) {
        return isInputTagWithType(element, "checkbox");
    }

    public static boolean isInputHiddenTag(WebElement element) {
        return isInputTagWithType(element, "hidden");
    }

    public static boolean isInputButtonTag(WebElement element) {
        return isInputTagWithType(element, "button");
    }

    public static boolean isInputSubmitTag(WebElement element) {
        return isInputTagWithType(element, "submit");
    }

    public static boolean isContentEditable(WebElement element) {
        if (element == null) {
            return false;
        }
        String contenteditable = element.getAttribute("contenteditable");
        if ("false".equalsIgnoreCase(contenteditable)) {
            return false;
        }
        if ("".equals(contenteditable) || "true".equalsIgnoreCase(contenteditable)) {
            return true;
        }
        // no contenteditable; or
        // concontenteditable == "inherit"; or
        // concontenteditable == "anything";
        // then we consider as "inherit"
        return isContentEditable(SelectorUtils.parent(element));
    }

}