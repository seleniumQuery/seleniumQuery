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

package endtoend.functions.jquery.forms.val;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;

import testinfrastructure.junitrule.SetUpAndTearDownDriver;
import testinfrastructure.junitrule.annotation.EdgeSkip;
import testinfrastructure.junitrule.annotation.OperaSkip;

public class ValFunction_OtherTest {

    @ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    private static final String ID_SELECT_1 = "#select-1";

    @Test
    @EdgeSkip("NoSuchWindowException -- TODO add tests for positive case")
    @OperaSkip("Expected: is 's1o2-value' but: was '' -- TODO check")
    public void val_read__SELECT___readsSelectedOptionsValue() {
        assertThat($(ID_SELECT_1).val(), is("s1o2-value"));
    }

    @Test
    @EdgeSkip("NoSuchWindowException -- TODO add tests for positive case")
    @OperaSkip("Expected: is 's1o1-value' but: was '' -- TODO check")
    public void val_read__OPTION__withValueAttribute__readsValueAttribute() {
        assertThat($("#option-1-of-select-1").val(), is("s1o1-value"));
        assertThat($("#option-2-of-select-1").val(), is("s1o2-value"));
    }

    @Test
    @EdgeSkip("NoSuchWindowException -- TODO add tests for positive case")
    @OperaSkip("Expected: is 's1o3-text' but: was '' -- TODO check")
    public void val_read__OPTION__withoutValueAttribute__readsText() {
        assertThat($("#option-3-of-select-1").val(), is("s1o3-text"));
    }

    @Test(expected = NoSuchElementException.class)
    @EdgeSkip("NoSuchWindowException -- TODO add tests for positive case")
    @OperaSkip("Didnt throw exception-- TODO check")
    public void val_write__SELECT___throwsException__ifThereIsNoOptionWithValue() {
        $(ID_SELECT_1).val("NO OPTION WITH THIS STRING AS VALUE");
    }

    @Test
    @EdgeSkip("NoSuchWindowException -- TODO add tests for positive case")
    @OperaSkip("Expected: is 's1o1-value' but: was '' -- TODO check")
    public void val_write__SELECT___changesValue__ifThere_Is_OptionWithValue() {
        // when
        $(ID_SELECT_1).val("s1o1-value");
        // then
        assertThat($(ID_SELECT_1).val(), is("s1o1-value"));
    }

    @Test
    @EdgeSkip("NoSuchWindowException -- TODO add tests for positive case")
    @OperaSkip("SEVERAL Expected: is 'X' but: was 'X' -- TODO check")
    public void val_others() {
        // TEXTAREA ----------------------------------------------------------------------------------------------------
        assertThat($("#ta").val().trim(), is("bozo"));
        assertThat($("#ta").val(), is("\t\tbozo\n\t")); // Firefox/Chrome/PhantomJS/IE10

        $("#ta").val("NEW-TEXTAREA-VALUE");
        assertThat($("#ta").val(), is("NEW-TEXTAREA-VALUE"));

        // <input type="text"> -----------------------------------------------------------------------------------------
        assertThat($("#i1").val(), is("ii!"));
        $("#i1").val("NEW-INPUT-TEXT-VALUE");
        assertThat($("#i1").val(), is("NEW-INPUT-TEXT-VALUE"));

        // <input type="radio"> ----------------------------------------------------------------------------------------
        assertThat($("#ir1").val(), is("input-radio-value"));
        $("#ir1").val("new-radio-value"); // #disagree - should have no effect
        //assertThat($("#ir1").val(), is("new-radio-value")); // #disagree - it didnt change as line above should have no effect
        assertThat($("#ir1").val(), is("input-radio-value")); // #disagree - so the value must be the same

        // <input type="checkbox"> -------------------------------------------------------------------------------------
        assertThat($("#ic1").val(), is("input-checkbox-value"));
        $("#ic1").val("new-checkbox-value"); // #disagree - should have no effect
        //assertThat($("#ic1").val(), is("new-checkbox-value")); // #disagree - it didnt change as line above should have no effect
        assertThat($("#ic1").val(), is("input-checkbox-value")); // #disagree - so the value must be the same

        // <input type="hidden"> ---------------------------------------------------------------------------------------
        assertThat($("#ih1").val(), is("input-hidden-value"));
        $("#ih1").val("new-hidden-value"); // #disagree - should have no effect
        assertThat($("#ih1").val(), is("input-hidden-value")); // #disagree - so the value must be the same

        // <input type="submit"> ---------------------------------------------------------------------------------------
        assertThat($("#is1").val(), is("input-submit-value"));
        $("#is1").val("new-submit-value"); // #disagree - should have no effect
        //assertThat($("#is1").val(), is("new-submit-value")); // #disagree - it didnt change as line above should have no effect
        assertThat($("#is1").val(), is("input-submit-value")); // #disagree - so the value must be the same

        // <input type="button"> ---------------------------------------------------------------------------------------
        assertThat($("#ib1").val(), is("input-button-value"));
        $("#ib1").val("new-button-value"); // #disagree - should have no effect
        //assertThat($("#ib1").val(), is("new-button-value")); // #disagree - it didnt change as line above should have no effect
        assertThat($("#ib1").val(), is("input-button-value")); // #disagree - so the value must be the same

        // <select id="select-option-without-value"> -------------------------------------------------------------------
        assertThat($("#select-option-without-value").val(), is("selected option without value"));
    }

}
