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

package integration.functions.jquery.traversing;

import infrastructure.junitrule.SetUpAndTearDownDriver;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class IsFunctionTest {

    @ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    @Test
    public void is_function() {
    	assertThat($("#myDiv").is("div"), is(true));
        assertThat($("#myDiv").is(".classOne"), is(true));
        assertThat($("#myDiv").is(".classTwo"), is(true));
        assertThat($("#myDiv").is(":not(.classThree)"), is(true));
        assertThat($("#myDiv").is(".classOne.classTwo"), is(true));
        assertThat($("#myDiv").is(".classOne.classTwo:not(.classThree)"), is(true));
        
        assertThat($("#otherDiv").is(".classThree"), is(true));
        assertThat($("#otherDiv").is(":not(.classOne)"), is(true));
        
        assertThat($("#o1").is("option"), is(true));
        assertThat($("#o2").is("option"), is(true));
        assertThat($("#o1").is(":not(:selected)"), is(true));
        assertThat($("#o2").is(":selected"), is(true));
        
        assertThat($("#o1").is(":not(:not(:not(:selected)))"), is(true));
        assertThat($("#o2").is(":not(:not(:selected))"), is(true));
    }
    
	@Test
	public void is_function__with_no_matched_sets() {
		assertThat($("#mothafocka").is("#moo"), is(false));
	}
	
	@Test
	public void is_function__with_very_permissive_sets() {
		assertThat($("*").is("*"), is(true));
		assertThat($("div").is("div"), is(true)); 
		assertThat($("div").is("*"), is(true)); 
		assertThat($("*").is("div"), is(true));
	}
    
}