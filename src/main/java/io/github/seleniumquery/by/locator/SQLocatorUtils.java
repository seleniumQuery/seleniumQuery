package io.github.seleniumquery.by.locator;

public class SQLocatorUtils {

    public static String conditionalSimpleXPathMerge(String leftXPathExpression, String rightXPathExpression) {
        if (leftXPathExpression == null || !leftXPathExpression.endsWith("]")) {
            throw new IllegalArgumentException("Left XPath expression is invalid.");
        }
        if (leftXPathExpression.equals(".//*[true()]")) {
            return ".//*[" + rightXPathExpression + "]";
        }
        if (leftXPathExpression.endsWith(" and true()]")) {
            return leftXPathExpression.substring(0, leftXPathExpression.length()-7) + rightXPathExpression + "]";
        }
        // because the previous was merged as a conditional, and we are a conditional as well, so we just 'AND it
        return leftXPathExpression.substring(0, leftXPathExpression.length()-1) + " and " + rightXPathExpression + "]";
    }

    /**
     * Merges two CSS selector parts into one.
     * @param leftCssSelector The left part of the selector.
     * @param rightSCssSelector The right part of the selector.
     * @return The two parts merged as a CSS selector.
     */
    public static String cssMerge(String leftCssSelector, String rightSCssSelector) {
        if (leftCssSelector.equals("*")) {
            return rightSCssSelector;
        }
        return leftCssSelector+rightSCssSelector;
    }

}