package io.github.seleniumquery.internal.browser.browserfunctions;

import org.openqa.selenium.JavascriptExecutor;

import io.github.seleniumquery.internal.browser.InternalTargetableBrowserFunctions;

public interface EvalFunction extends InternalTargetableBrowserFunctions {

    @SuppressWarnings("unchecked")
    default <T> T eval(String javaScriptCode, Object... args) {
        return (T) ((JavascriptExecutor) target().driver().get()).executeScript(javaScriptCode, args);
    }

}
