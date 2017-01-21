package io.github.seleniumquery.by.secondgen.csstree.condition;

import io.github.seleniumquery.by.secondgen.csstree.condition.attribute.SQCssClassAttributeCondition;
import io.github.seleniumquery.by.secondgen.csstree.condition.attribute.SQCssIdAttributeCondition;
import io.github.seleniumquery.by.secondgen.csstree.selector.CssSelector;
import io.github.seleniumquery.by.secondgen.finder.ElementFinder;
import io.github.seleniumquery.by.secondgen.parser.SQParseTreeBuilder;
import org.junit.Test;

import static io.github.seleniumquery.by.secondgen.finder.ElementFinderUtilsTest.UNIVERSAL_SELECTOR_FINDER;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.assertThat;

public class SQCssAndConditionTest {

    @Test
    public void toElementFinder() {
        // given
        SQCssIdAttributeCondition idCondition = new SQCssIdAttributeCondition("my-id");
        SQCssClassAttributeCondition classCondition = new SQCssClassAttributeCondition("class-name");
        SQCssAndCondition andCondition = new SQCssAndCondition(idCondition, classCondition);
        // when
        ElementFinder elementFinder = andCondition.toElementFinder(UNIVERSAL_SELECTOR_FINDER);
        // then
        assertThat(elementFinder.toCssString(), is("#my-id.class-name"));
        assertThat(elementFinder.canFetchThroughCssAlone(), is(true));
        assertThat(elementFinder.getXPathExpression(), is(".//*[@id = 'my-id' and contains(concat(' ', normalize-space(@class), ' '), ' class-name ')]"));
        assertThat(elementFinder.getElementFilterList().getElementFilters(), empty());
    }

    @Test
    public void toElementFinder__with_two_ands() {
        // given
        String tagClassClassSelector = "span.a.b";
        CssSelector tagAndClassAndClassCondition = SQParseTreeBuilder.parse(tagClassClassSelector).firstSelector();
        // when
        ElementFinder elementFinder = tagAndClassAndClassCondition.toElementFinder(UNIVERSAL_SELECTOR_FINDER);
        // then
        assertThat(elementFinder.toCssString(), is(tagClassClassSelector));
        assertThat(elementFinder.canFetchThroughCssAlone(), is(true));
        assertThat(elementFinder.getXPathExpression(), is(".//*[self::span and contains(concat(' ', normalize-space(@class), ' '), ' a ') and contains(concat(' ', normalize-space(@class), ' '), ' b ')]"));
        assertThat(elementFinder.getElementFilterList().getElementFilters(), empty());
    }

}
