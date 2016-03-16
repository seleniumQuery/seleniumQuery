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

public class AttrFunctionTest {

	@ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

	// http://jsbin.com/pupoj/5/edit
    @Test @JavaScriptOnly
    public void attr_function__getting_and_setting_whatever_to_CHECKED_is_like_setting_to_CHECKED_string() {
    	// whatever value checked is, if it exists, attr returns "checked"
    	assertThat($("#x1").attr("checked"), is("checked"));
    	assertThat($("#x2").attr("checked"), is("checked"));
    	assertThat($("#x3").attr("checked"), is("checked"));
    	assertThat($("#x4").attr("checked"), is("checked"));
    	assertThat($("#x5").attr("checked"), is("checked"));
    	assertThat($("#x6").attr("checked"), is("checked"));

    	// when it doesn't, attr returns undefined
    	assertThat($("#n1").attr("checked"), is(nullValue()));

    	// when you set any value to checked, it sets "checked"
    	$("#n1").removeAttr("checked"); // reset
//    	$("#n1").attr("checked", null);
    	assertThat($("#n1").attr("checked"), is(nullValue()));

    	$("#n1").removeAttr("checked"); // reset
    	$("#n1").attr("checked", "");
    	assertThat($("#n1").attr("checked"), is("checked"));

    	$("#n1").removeAttr("checked"); // reset
    	$("#n1").attr("checked", "checked");
    	assertThat($("#n1").attr("checked"), is("checked"));

    	$("#n1").removeAttr("checked"); // reset
    	$("#n1").attr("checked", "true");
    	assertThat($("#n1").attr("checked"), is("checked"));

    	$("#n1").removeAttr("checked"); // reset
    	$("#n1").attr("checked", "false");
    	assertThat($("#n1").attr("checked"), is("checked"));

    	$("#n1").removeAttr("checked"); // reset
    	$("#n1").attr("checked", "abc");
    	assertThat($("#n1").attr("checked"), is("checked"));
    }
    
    @Test @JavaScriptOnly
    public void attr_function__does_the_same_CHECKED_stuff_to_SELECTED_no_matter_what_tag() {
    	// no matter what tag, the "checked" policy remains!
    	$("#d1").attr("checked", "");
    	assertThat($("#d1").attr("checked"), is("checked"));
    	$("#d1").attr("checked", "yo");
    	assertThat($("#d1").attr("checked"), is("checked"));

    	// the same is valid to "selected"
    	$("#d1").attr("selected", "");
    	assertThat($("#d1").attr("selected"), is("selected"));
    	$("#d1").attr("selected", "yo");
    	assertThat($("#d1").attr("selected"), is("selected"));
    }
    
    @Test @JavaScriptOnly
    public void attr_function__getting_and_setting() {
    	assertThat($("#chk1").attr("checked"), is("checked"));
    	assertThat($("#chk1").<Boolean>prop("checked"), is(true));
    	
    	$("#chk1").prop("checked", true);
    	assertThat($("#chk1").<Boolean>prop("checked"), is(true));
    	assertThat($("#chk1").attr("checked"), is("checked"));
    	$("#chk1").prop("checked", false);
    	
		assertThat($("#chk1").attr("checked"), is("checked"));
    	assertThat($("#chk1").<Boolean>prop("checked"), is(false));
    }

    @Test @JavaScriptOnly
    public void attr_function__reads_attribute_well__AND__attribute_is_not_read_by_prop_function() {
        String dataBallAttributeValue = $("#chk1").attr("data-ball");
        assertThat(dataBallAttributeValue, is("yo"));

        String dataBallAttributeViaProp = $("#chk1").prop("data-ball");
		assertThat(dataBallAttributeViaProp, is(nullValue()));
    }

    @Test @JavaScriptOnly
    public void attr_function__setting() {
        // given
        // original test html file
        // when
        $("#chk1").attr("data-ball", "abc");
        // then
        assertThat($("#chk1").attr("data-ball"), is("abc"));
    }

}