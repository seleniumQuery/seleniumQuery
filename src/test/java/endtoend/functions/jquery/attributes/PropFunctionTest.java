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

package endtoend.functions.jquery.attributes;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import testinfrastructure.junitrule.JavaScriptOnly;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class PropFunctionTest {

    @ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

	// http://jsbin.com/zofekalo/1/edit
    @Test @JavaScriptOnly
    public void prop_function__getting() throws Exception {
        assertThat($("#chk_checked").<Boolean>prop("checked"), is(true));
        assertThat($("#chk_not_checked").<Boolean>prop("checked"), is(false));
        
        assertThat($("#rad_checked").<Boolean>prop("checked"), is(true));
        assertThat($("#rad_not_checked").<Boolean>prop("checked"), is(false));
        
        assertThat($("#opt_selected").prop("checked"), is(nullValue()));
        assertThat($("#opt_not_selected").prop("checked"), is(nullValue()));
        
        assertThat($("#chk_checked").prop("selected"), is(nullValue()));
        assertThat($("#chk_not_checked").prop("selected"), is(nullValue()));
        
        assertThat($("#rad_checked").prop("selected"), is(nullValue()));
        assertThat($("#rad_not_checked").prop("selected"), is(nullValue()));
        
        assertThat($("#opt_selected").<Boolean>prop("selected"), is(true));
        assertThat($("#opt_not_selected").<Boolean>prop("selected"), is(false));
    }
    
    // http://jsbin.com/ceqijima/2/edit
    @Test @JavaScriptOnly
    public void prop_function__setting_SELECTED_prop() throws Exception {
    	setPropAndVerify(true, true);
    	setPropAndVerify(1, true);
    	setPropAndVerify(0, false);
    	setPropAndVerify("1", true);
    	setPropAndVerify("0", true);
    	setPropAndVerify("a", true);
    	setPropAndVerify("", false);
    	setPropAndVerify(false, false);
    }
	
	private void setPropAndVerify(Object val, Object expected) {
		reset();
		$("#c1 .other").prop("selected", val);
		assertThat($("#c1 .other").prop("selected"), is(expected));
	}
	
	private void reset() {
		$("#c1 .initial").prop("selected", true);
	}

}