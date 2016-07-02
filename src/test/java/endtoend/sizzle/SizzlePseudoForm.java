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

package endtoend.sizzle;

import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;
import testinfrastructure.junitrule.annotation.JavaScriptEnabledOnly;

public class SizzlePseudoForm extends SizzleTest {

    @ClassRule @Rule  public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(SizzleTest.class);

    @Test @JavaScriptEnabledOnly
    public void pseudo_form() {
        createInputs();
        t("Form element :input", "#form :input", new String[]{"text1", "text2", "radio1", "radio2", "check1", "check2", "hidden1", "hidden2", "name", "search", "button", "area1", "select1", "select2", "select3", "select4", "select5", "impliedText", "capitalText"});
        t("Form element :radio", "#form :radio", new String[]{"radio1", "radio2"});
        t("Form element :checkbox", "#form :checkbox", new String[]{"check1", "check2"});
        t("Form element :text", "#form :text", new String[]{"text1", "text2", "hidden2", "name", "impliedText", "capitalText"});
        removeInputs();
    }

    private void createInputs() {
        executeJS("jQuery('<input id=\"impliedText\"/><input id=\"capitalText\" type=\"TEXT\">').appendTo('#form');");
    }

    private void removeInputs() {
        executeJS("jQuery('#impliedText,#capitalText').remove()");
    }

    @Test
    @Ignore("Issue#94")
    public void pseudo_form__checkedPseudoClass() {
        t("Form element :radio:checked", "#form :radio:checked", new String[]{"radio2"});
        t("Form element :checkbox:checked", "#form :checkbox:checked", new String[]{"check1"});
        t("Form element :radio:checked, :checkbox:checked", "#form :radio:checked, #form :checkbox:checked", new String[]{"radio2", "check1"});
        t("Selected Option Element are also :checked", "#form option:checked", new String[]{"option1a", "option2d", "option3b", "option3c", "option4b", "option4c", "option4d", "option5a"});
    }

    @Test
    @Ignore("Issue#86")
    public void pseudo_form__selectedPseudoClass() {
        t("Selected Option Element", "#form option:selected", new String[]{"option1a", "option2d", "option3b", "option3c", "option4b", "option4c", "option4d", "option5a"});
    }

    @Test
    public void pseudo_form__hidden() {
        t("Hidden inputs should be treated as enabled. See QSA test.", "#hidden1:enabled", new String[]{"hidden1"});
    }

}
