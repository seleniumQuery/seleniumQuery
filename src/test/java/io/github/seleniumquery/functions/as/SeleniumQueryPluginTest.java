package io.github.seleniumquery.functions.as;

import io.github.seleniumquery.SeleniumQueryObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static infrastructure.IntegrationTestUtils.htmlTestFileUrl;
import static io.github.seleniumquery.SeleniumQuery.$;
import static io.github.seleniumquery.functions.as.Sizer.SIZER;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

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
        public Sizer as(SeleniumQueryObject seleniumQueryObject) {
            return new Sizer(seleniumQueryObject);
        }
    };

    private SeleniumQueryObject seleniumQueryObject;
    public Sizer(SeleniumQueryObject seleniumQueryObject) {
        this.seleniumQueryObject = seleniumQueryObject;
    }

    public int gimmeTheSize() {
        return this.seleniumQueryObject.get().size();
    }

}