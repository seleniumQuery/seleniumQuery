/*
 * Copyright (c) 2016 seleniumQuery authors
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

package endtoend.selectors.combinators;

import io.github.seleniumquery.SeleniumQuery;
import io.github.seleniumquery.by.firstgen.css.CssSelectorMatcherService;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class GeneralAdjacentTest {

	@ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

	private WebDriver driver;
	
	@Before
	public void before() {
		driver = SeleniumQuery.$.driver().get();
	}
	
    // E ~ F
	@Test
	public void general_adjacent() {
		WebElement optionElement = driver.findElement(By.cssSelector(".e4"));
		assertElementMatchesSelectorOrNot(optionElement, "option ~ option", true);
		assertElementMatchesSelectorOrNot(optionElement, "select ~ option", false);
		assertElementMatchesSelectorOrNot(optionElement, "option ~ select", false);

		WebElement brazilian_p = driver.findElement(By.id("brazilian-p"));
		assertElementMatchesSelectorOrNot(brazilian_p, "p ~ p", false);
		assertElementMatchesSelectorOrNot(brazilian_p, "body ~ p", false);
		assertElementMatchesSelectorOrNot(brazilian_p, "select ~ p", false);
		assertElementMatchesSelectorOrNot(brazilian_p, "p ~ select", false);

		WebElement french_p = driver.findElement(By.id("french-p"));
		assertElementMatchesSelectorOrNot(french_p, "p ~ p", true);
		assertElementMatchesSelectorOrNot(french_p, "body ~ p", false);
		assertElementMatchesSelectorOrNot(french_p, "select ~ p", false);
		assertElementMatchesSelectorOrNot(french_p, "p ~ select", false);

		WebElement hero_combo = driver.findElement(By.id("hero-combo"));
		assertElementMatchesSelectorOrNot(hero_combo, "p ~ select", true);
		assertElementMatchesSelectorOrNot(hero_combo, "select ~ option", false);

		WebElement containsDiv = driver.findElement(By.id("containsDiv"));
		assertElementMatchesSelectorOrNot(containsDiv, "p ~ div", true);
		assertElementMatchesSelectorOrNot(containsDiv, "select ~ div", true);
		assertElementMatchesSelectorOrNot(containsDiv, "div ~ div", true);
		assertElementMatchesSelectorOrNot(containsDiv, "div ~ *", true);
		assertElementMatchesSelectorOrNot(containsDiv, "p ~ p ~ *", true);
		assertElementMatchesSelectorOrNot(containsDiv, "p ~ p ~ div", true);
	}

	private void assertElementMatchesSelectorOrNot(WebElement containsDiv, String selector, boolean matchesOrNot) {
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, containsDiv, selector), is(matchesOrNot));
	}

}