package endtoend.sizzle;

import infrastructure.junitrule.JavaScriptOnly;
import infrastructure.junitrule.SetUpAndTearDownDriver;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

public class SizzlePseudoForm extends SizzleTest {

    @ClassRule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver(SizzleTest.class);
    @Rule public SetUpAndTearDownDriver setUpAndTearDownDriverRuleInstance = setUpAndTearDownDriverRule;

    @Test @JavaScriptOnly
    public void pseudo_form() throws Exception {
        executeJS("jQuery('<input id=\"impliedText\"/><input id=\"capitalText\" type=\"TEXT\">').appendTo('#form');");

        t("Form element :input", "#form :input", new String[]{"text1", "text2", "radio1", "radio2", "check1", "check2", "hidden1", "hidden2", "name", "search", "button", "area1", "select1", "select2", "select3", "select4", "select5", "impliedText", "capitalText"});
        t("Form element :radio", "#form :radio", new String[]{"radio1", "radio2"});
        t("Form element :checkbox", "#form :checkbox", new String[]{"check1", "check2"});
        t("Form element :text", "#form :text", new String[]{"text1", "text2", "hidden2", "name", "impliedText", "capitalText"});
        t("Form element :radio:checked", "#form :radio:checked", new String[]{"radio2"});
        t("Form element :checkbox:checked", "#form :checkbox:checked", new String[]{"check1"});
        t("Form element :radio:checked, :checkbox:checked", "#form :radio:checked, #form :checkbox:checked", new String[]{"radio2", "check1"});

        t("Selected Option Element", "#form option:selected", new String[]{"option1a", "option2d", "option3b", "option3c", "option4b", "option4c", "option4d", "option5a"});
        t("Selected Option Element are also :checked", "#form option:checked", new String[]{"option1a", "option2d", "option3b", "option3c", "option4b", "option4c", "option4d", "option5a"});

        t("Hidden inputs should be treated as enabled. See QSA test.", "#hidden1:enabled", new String[]{"hidden1"});

        executeJS("jQuery('#impliedText,#capitalText').remove()");
    }

}