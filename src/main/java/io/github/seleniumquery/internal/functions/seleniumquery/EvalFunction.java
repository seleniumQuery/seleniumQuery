package io.github.seleniumquery.internal.functions.seleniumquery;

import org.openqa.selenium.JavascriptExecutor;

import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.internal.InternalTargetableSqObject;

public interface EvalFunction extends InternalTargetableSqObject {

    @SuppressWarnings("unchecked")
    default <T> T eval(String javaScriptCode, Object... args) {
        SeleniumQueryObject target = target();
        return (T) ((JavascriptExecutor) target.getWebDriver()).executeScript(javaScriptCode, target.toArray(), args);
    }

}
