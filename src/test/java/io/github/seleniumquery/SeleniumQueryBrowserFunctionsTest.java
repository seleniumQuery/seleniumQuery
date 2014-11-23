package io.github.seleniumquery;

import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SeleniumQueryBrowserFunctionsTest {

    SeleniumQueryBrowserFunctions seleniumQueryBrowserFunctions = new SeleniumQueryBrowserFunctions();

    @Test
    @SuppressWarnings("deprecation")
    public void pause__should_pause_for_the_given_time_in_millis() {
        // given
        long startingTime = System.currentTimeMillis();
        // when
        seleniumQueryBrowserFunctions.pause(1000);
        // then
        long timeSpent = System.currentTimeMillis() - startingTime;
        assertThat(new BigDecimal(timeSpent), is(closeTo(new BigDecimal(1000), new BigDecimal(100))));
    }

}