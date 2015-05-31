/*
 * Copyright (c) 2015 seleniumQuery authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package endtoend.io.github.seleniumquery.by.css;

import infrastructure.junitrule.SetUpAndTearDownDriver;
import io.github.seleniumquery.SeleniumQuery;
import io.github.seleniumquery.by.css.CssSelectorMatcherService;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CssSelectorMatcherServiceTest {

	@ClassRule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();
	@Rule public SetUpAndTearDownDriver setUpAndTearDownDriverRuleInstance = setUpAndTearDownDriverRule;

	WebDriver driver;
	
	@Before
	public void before() {
		driver = SeleniumQuery.$.driver().get();
	}
	
    // E ~ F
	@Test
	public void general_adjacent() {
		WebElement optionElement = driver.findElement(By.cssSelector(".e4"));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, optionElement, "option ~ option"), is(true));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, optionElement, "select ~ option"), is(false));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, optionElement, "option ~ select"), is(false));
		
		WebElement brazilian_p = driver.findElement(By.id("brazilian-p"));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, brazilian_p, "p ~ p"), is(false));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, brazilian_p, "body ~ p"), is(false));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, brazilian_p, "select ~ p"), is(false));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, brazilian_p, "p ~ select"), is(false));
		
		WebElement french_p = driver.findElement(By.id("french-p"));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, french_p, "p ~ p"), is(true));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, french_p, "body ~ p"), is(false));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, french_p, "select ~ p"), is(false));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, french_p, "p ~ select"), is(false));
		
		WebElement hero_combo = driver.findElement(By.id("hero-combo"));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, hero_combo, "p ~ select"), is(true));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, hero_combo, "select ~ option"), is(false));
		
		WebElement containsDiv = driver.findElement(By.id("containsDiv"));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, containsDiv, "p ~ div"), is(true));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, containsDiv, "select ~ div"), is(true));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, containsDiv, "div ~ div"), is(true));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, containsDiv, "div ~ *"), is(true));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, containsDiv, "p ~ p ~ *"), is(true));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, containsDiv, "p ~ p ~ div"), is(true));
	}

}