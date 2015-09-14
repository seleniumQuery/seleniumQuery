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

package endtoend.selectors.attributes;

import io.github.seleniumquery.by.firstgen.css.CssSelectorMatcherService;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.CSSParseException;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AttributeSelectorsTest {

	@ClassRule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();
	@Rule public SetUpAndTearDownDriver setUpAndTearDownDriverRuleInstance = setUpAndTearDownDriverRule;
	
	WebDriver driver;
	
	@Before
	public void before() {
		driver = $.driver().get();
	}
	
	/* Has Attribute Selector: [name]
	 * Selects elements that have the specified attribute, with any value.
	 */
	@Test
	public void has_attribute() {
        WebElement myA = driver.findElement(By.id("myA"));
			
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a"), is(true));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[rel]"), is(true));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[bozo]"), is(false));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[data-title]"), is(false));
		assertThat($("#myA").is("a"), is(true));
		assertThat($("#myA").is("a[rel]"), is(true));
		assertThat($("#myA").is("a[bozo]"), is(false));
		assertThat($("#myA").is("a[data-title]"), is(false));
		
		// case INsensitive
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[rEL]"), is(true));
		assertThat($("#myA").is("a[rEL]"), is(true));
	}
	
	/* http://api.jquery.com/category/selectors/attribute-selectors/
	 * Attribute values in selector expressions must follow the rules for W3C CSS selectors;
	 * in general, that means anything other than a valid identifier should be surrounded by quotation marks.
	 */
	@Test(expected=CSSParseException.class)
	public void attribute_equals_unquoted_space_is_not_valid() {
		WebElement myA = driver.findElement(By.id("myA"));
		CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[rel=nofollow self]"); // should throw exception
	}
	
	/* Attribute Equals Selector: [name="value"]
	 * Selects elements that have the specified attribute with a value exactly equal to a certain value.
	 */
	@Test
	public void attribute_equals() {
        WebElement myA = driver.findElement(By.id("myA"));
		WebElement myA2 = driver.findElement(By.id("myA2"));
			
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[rel=\"nofollow self\"]"), is(true));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[rel='nofollow self']"), is(true));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA2, "a[hreflang=en]"), is(true));
		
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[attrDOESnotExist=yay]"), is(false));
		
		// case INsensitive
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[rEL='noFOLLow sELf']"), is(true));
	}
	
	/* Attribute Contains Word Selector: [name~="value"]
	 * Selects elements that have the specified attribute with a value containing a given word, delimited by spaces.
	 */
	@Test
	public void attribute_contains_word() {
		WebElement myA = driver.findElement(By.id("myA"));
		
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[rel~=nofollow]"), is(true));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[rel~=self]"), is(true));
		
		// only whole words
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[rel~=nofollo]"), is(false));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[rel~=sel]"), is(false));
		
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[rel~=\"nofollow\"]"), is(true));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[rel~=\"self\"]"), is(true));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[rel~='nofollow']"), is(true));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[rel~='self']"), is(true));
		
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[rel~=\"nofollow self\"]"), is(false));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[rel~='nofollow self']"), is(false));
		
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[rel~=nofollowself]"), is(false));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[rel~=selfie]"), is(false));
		
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[nofollow~=no]"), is(false));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[rel~=re]"), is(false));
		
		
		// case INsensitive
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[REl~=noFOLLow]"), is(true));
	}
	
	/* Attribute Contains Prefix Selector: [name|="value"]
	 * Selects elements that have the specified attribute with a value either equal to a given string or starting with that string followed by a hyphen (-).
	 */
	@Test
	public void attribute_contains_prefix() {
		WebElement myA2 = driver.findElement(By.id("myA2"));
		WebElement myA3 = driver.findElement(By.id("myA3"));
		WebElement myA4 = driver.findElement(By.id("myA4"));
		WebElement myA5 = driver.findElement(By.id("myA5"));
		WebElement myA6 = driver.findElement(By.id("myA6"));
		
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA2, "a[hreflang|=en]"), is(true));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA3, "a[hreflang|=en]"), is(true));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA4, "a[hreflang|=en]"), is(false));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA5, "a[hreflang|=en]"), is(false));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA6, "a[hreflang|=en]"), is(true));
		
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA5, "a[hreflang|=ex]"), is(true));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA5, "a[hreflang|=fr]"), is(false));
		
		// case INsensitive
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA2, "a[hREFlaNg|=eN]"), is(true));
	}
	
	/* Attribute Starts With Selector: [name^="value"]
	 * Selects elements that have the specified attribute with a value beginning exactly with a given string.
	 */
	@Test
	public void attribute_starts_with() {
		WebElement myA = driver.findElement(By.id("myA"));
		
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[rel^=nofollow]"), is(true));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[rel^=self]"), is(false));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[rel^=\"nofollow\"]"), is(true));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[rel^=\"self\"]"), is(false));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[rel^='nofollow']"), is(true));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[rel^='self']"), is(false));
		
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[rel^=\"nofollow self\"]"), is(true));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[rel^='nofollow self']"), is(true));
		
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[rel^=nofollowself]"), is(false));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[rel^=selfie]"), is(false));
		
		// case insensitive
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[ReL^=noFOLLow]"), is(true));
	}
	
	/* Attribute Ends With Selector: [name$="value"]
	 * Selects elements that have the specified attribute with a value ending exactly with a given string. The comparison is case sensitive.
	 */
	@Test
	public void attribute_ends_with() {
		WebElement myA = driver.findElement(By.id("myA"));
		
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[rel$=nofollow]"), is(false));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[rel$=self]"), is(true));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[rel$=\"nofollow\"]"), is(false));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[rel$=\"self\"]"), is(true));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[rel$='nofollow']"), is(false));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[rel$='self']"), is(true));
		
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[rel$=\"nofollow self\"]"), is(true));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[rel$='nofollow self']"), is(true));
		
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[rel$=nofollowself]"), is(false));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[rel$=selfie]"), is(false));
		
		// case SENSITIVE (only the string)
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[ReL$=self]"), is(true));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[rel$=sELf]"), is(false));
	}
	
	/* Attribute Contains Selector: [name*="value"]
	 * Selects elements that have the specified attribute with a value containing the a given substring.
	 */
	@Test
	public void attribute_contains_substring() {
		WebElement myA = driver.findElement(By.id("myA"));
		
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[rel*=nofollow]"), is(true));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[rel*=self]"), is(true));
		
		// substrings are OK
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[rel*=nofollo]"), is(true));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[rel*=sel]"), is(true));
		
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[rel*=\"nofollow\"]"), is(true));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[rel*=\"self\"]"), is(true));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[rel*='nofollow']"), is(true));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[rel*='self']"), is(true));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[rel*=\"nofollow self\"]"), is(true));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[rel*='nofollow self']"), is(true));
		
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[rel*=nofollowself]"), is(false));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[rel*=selfie]"), is(false));
		
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[nofollow*=no]"), is(false));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[rel*=re]"), is(false));
		
		// case INsensitive
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[REl*=noFOLL]"), is(true));
	}

	/* Attribute Not Equal Selector: [name!="value"] -- equivalent to :not([name="value"])
	 * Select elements that either don't have the specified attribute, or do have the specified attribute but not with a certain value.
	 */
	@Test
	public void attribute_not_equals() {
        WebElement myA = driver.findElement(By.id("myA"));
		WebElement myA2 = driver.findElement(By.id("myA2"));
			
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[rel!=\"nofollow self\"]"), is(false));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[rel!='nofollow self']"), is(false));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA2, "a[hreflang!=en]"), is(false));
		
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[attrDOESnotExist!=yay]"), is(true));
		
		// case INsensitive
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, myA, "a[rEL!='noFOLLow sELf']"), is(false));
	}
	
	/* #id
	 */
	@Test
	public void id() {
		WebElement span = driver.findElement(By.tagName("span"));
		
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, span, "#somId"), is(false));
		
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, driver.findElement(By.id("d1")), "#d1"), is(true));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, driver.findElement(By.id("d1")), "#d2"), is(false));
	}
	
	/* .className
	 */
	@Test
	public void className_and_id() {
		WebElement span = driver.findElement(By.tagName("span"));
		
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, span, ".someClass"), is(false));
		
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, driver.findElement(By.id("d1")), ".one"), is(true));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, driver.findElement(By.id("d2")), ".one"), is(true));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, driver.findElement(By.id("d3")), ".one"), is(true));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, driver.findElement(By.id("d4")), ".one"), is(true));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, driver.findElement(By.id("d5")), ".one"), is(true));
		assertThat(CssSelectorMatcherService.elementMatchesStringSelector(driver, driver.findElement(By.id("d6")), ".one"), is(true));
	}

}