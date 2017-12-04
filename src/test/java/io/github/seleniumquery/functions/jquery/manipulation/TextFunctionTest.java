package io.github.seleniumquery.functions.jquery.manipulation;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.WebElement;

import testinfrastructure.testdouble.org.openqa.selenium.WebElementStub;

public class TextFunctionTest {

    private static class WebElementText extends WebElementStub {
        private final String text;
        private WebElementText(String text) {
            this.text = text;
        }
        @Override
        public String getText() {
            return this.text;
        }
    }

    @Test
    public void text() {
        // given
        List<WebElement> els = asList(new WebElementText("AAA"), new WebElementText("BBB"), new WebElementText("CCC"));
        // when
        String text = TextFunction.text(els);
        // then
        assertEquals("AAA BBB CCC", text);
    }

}
