package io.github.seleniumquery.functions.jquery.traversing.filtering.filterfunction;

import io.github.seleniumquery.SeleniumQueryObject;
import org.junit.Test;

import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.*;
import static testinfrastructure.testdouble.SeleniumQueryObjectMother.createStubSeleniumQueryObjectWithAtLeastOneElement;

public class FilterSelectorFunctionTest {

    private static final String NULL_SELECTOR = null;

    FilterSelectorFunction filterSelectorFunction = new FilterSelectorFunction();

    @Test
    public void null_selector__should_return_EMPTY_element_set() {
        // given
        SeleniumQueryObject targetSQO = createStubSeleniumQueryObjectWithAtLeastOneElement();
        // when
        SeleniumQueryObject resultSQO = filterSelectorFunction.filter(targetSQO, NULL_SELECTOR);
        // then
        assertThat(resultSQO.get(), empty());
    }

    @Test
    public void emptyString_selector__should_return_EMPTY_element_set() {
        // given
        SeleniumQueryObject targetSQO = createStubSeleniumQueryObjectWithAtLeastOneElement();
        // when
        SeleniumQueryObject resultSQO = filterSelectorFunction.filter(targetSQO, "");
        // then
        assertThat(resultSQO.get(), empty());
    }

    // TODO filter(this, "selector") --> should keep everyone that matches the isFunction("selector")

}