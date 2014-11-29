package io.github.seleniumquery.functions.as;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static infrastructure.IntegrationTestUtils.htmlTestFileUrl;
import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AsSelectTest {

    @Before
    public void setUp() throws Exception {
        $.driver().useHtmlUnit();
        $.url(htmlTestFileUrl(AsSelectTest.class));
        assertThat($("select").val(), is("valueA")); // negative test
        assertThat($("input").val(), is("inputValueShouldNotBeAffected")); // negative test
    }

    @After
    public void tearDown() throws Exception {
        assertThat($("input").val(), is("inputValueShouldNotBeAffected")); // negative test
        $.quit();
    }

    @Test
    public void selectByVisibleText() {
        // given
        // when
        $("select, input").as().select().selectByVisibleText("VisibleTextB");
        // then
        assertThat($("select").val(), is("valueB"));
    }

    @Test
    public void testSelectByValue() throws Exception {
        // given
        // when
        $("select, input").as().select().selectByValue("valueC");
        // then
        assertThat($("select").val(), is("valueC"));
    }

}