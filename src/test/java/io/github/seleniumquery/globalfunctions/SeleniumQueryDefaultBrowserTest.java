package io.github.seleniumquery.globalfunctions;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class SeleniumQueryDefaultBrowserTest {

	SeleniumQueryDefaultBrowser seleniumQueryDefaultBrowser = new SeleniumQueryDefaultBrowser();

	@Test
	public void sleepInt__should_sleep_for_the_given_time_in_millis() {
		// given
		long tempoNoInicio = System.currentTimeMillis();
		// when
		seleniumQueryDefaultBrowser.sleep(1 * 1000);
		// then
		long tempoGasto = System.currentTimeMillis() - tempoNoInicio;
		assertThat(new BigDecimal(tempoGasto), is(closeTo(new BigDecimal(1000), new BigDecimal(100))));
	}
	
	@Test
	public void sleepIntTimeUnit__should_sleep_for_the_given_time_unit__eg_SECONDS() {
		// given
		long tempoNoInicio = System.currentTimeMillis();
		// when
		seleniumQueryDefaultBrowser.sleep(1, TimeUnit.SECONDS);
		// then
		long tempoGasto = System.currentTimeMillis() - tempoNoInicio;
		assertThat(new BigDecimal(tempoGasto), is(closeTo(new BigDecimal(1000), new BigDecimal(100))));
	}
	
	@Test
	public void sleepIntTimeUnit__should_sleep_for_the_given_time_unit__eg_MILLISECONDS() {
		// given
		long tempoNoInicio = System.currentTimeMillis();
		// when
		seleniumQueryDefaultBrowser.sleep(1, TimeUnit.MILLISECONDS);
		// then
		long tempoGasto = System.currentTimeMillis() - tempoNoInicio;
		assertThat(new BigDecimal(tempoGasto), is(closeTo(new BigDecimal(1), new BigDecimal(100))));
	}

}