package io.github.seleniumquery.functions.jquery.traversing.filtering.filterfunction;

import io.github.seleniumquery.SeleniumQueryObject;
import org.openqa.selenium.WebElement;

import java.util.List;

import static io.github.seleniumquery.InternalSeleniumQueryObjectFactory.instance;
import static java.util.Collections.emptyList;

public class FilterSelectorFunction {

    public SeleniumQueryObject filter(SeleniumQueryObject seleniumQueryObject, String selector) {
        List<WebElement> filteredWebElements = emptyList();
        return instance().create(seleniumQueryObject.getSeleniumQueryFunctions(),
                seleniumQueryObject.getWebDriver(),
                null,
                filteredWebElements,
                seleniumQueryObject);
    }

}
