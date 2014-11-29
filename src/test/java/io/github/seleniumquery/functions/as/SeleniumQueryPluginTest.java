package io.github.seleniumquery.functions.as;

import io.github.seleniumquery.SeleniumQueryObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

import static infrastructure.IntegrationTestUtils.htmlTestFileUrl;
import static io.github.seleniumquery.SeleniumQuery.$;
import static io.github.seleniumquery.functions.as.Sizer.SIZER;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SeleniumQueryPluginTest {

    @Before
    public void setUp() throws Exception {
        $.driver().useHtmlUnit();
        $.url(htmlTestFileUrl(SeleniumQueryPluginTest.class));
        assertThat($("div").size(), is(7)); // negative test
    }

    @After
    public void tearDown() throws Exception {
        $.quit();
    }

    @Test
    public void as__should_accept_a_seleniumQueryPlugin_argument() throws Exception {
        // given
        // when
        SeleniumQueryObject sq = $("div");
        int theSize = sq.as(SIZER).gimmeTheSize();
        // then
        assertThat(theSize, is(7));
    }

}

class Sizer {
    public static final SeleniumQueryPlugin<Sizer> SIZER = new SeleniumQueryPlugin<Sizer>() {
        @Override
        public Sizer as(SeleniumQueryObject seleniumQueryObject, List<WebElement> elements) {
            return new Sizer(seleniumQueryObject, elements);
        }
    };

    @SuppressWarnings({"unused", "FieldCanBeLocal"}) // may, of course, not even use all args
    private SeleniumQueryObject seleniumQueryObject;
    private List<WebElement> elements;
    public Sizer(SeleniumQueryObject seleniumQueryObject, List<WebElement> elements) {
        this.seleniumQueryObject = seleniumQueryObject;
        this.elements = elements;
    }

    public int gimmeTheSize() {
        return this.elements.size();
    }

}