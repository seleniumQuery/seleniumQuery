package io.github.seleniumquery.internal.browser.browserfunctions;

import io.github.seleniumquery.internal.browser.InternalTargetableBrowserFunctions;

public interface TitleFunction extends InternalTargetableBrowserFunctions {

    @Override
    default String title() {
        return target().driver().get().getTitle();
    }

}
