package endtoend.utils;

import static io.github.seleniumquery.SeleniumQuery.$;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.utils.DriverVersionUtils;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

public class WebElementUtilsTest {

    @ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    @Test
    public void eval_return() {
        assertIsContentEditable("#aa", true);
        assertIsContentEditable("#bb", false);
        assertIsContentEditable("#cc", true);
        assertIsContentEditable("#dd", false);
        assertIsContentEditable("#ee", true);
        assertIsContentEditable("#ff", true);
        assertIsContentEditable("#gg", false);
        assertIsContentEditable("#hh", false);
    }

    private void assertIsContentEditable(String selector, boolean isCE) {
        SeleniumQueryObject $el = $(selector);
        if (!DriverVersionUtils.isHtmlUnitWithDisabledJavaScript($el)) {
            $el.assertThat().prop("isContentEditable").isEqualTo(isCE);
        }
        Assert.assertEquals(selector + " failed isContentEditable test", isContentEditableXPath($el), isCE);
    }

    private boolean isContentEditableXPath(SeleniumQueryObject $el) {
        WebElement element = $el.get(0);
        // hard copy of WebElementUtils#isContentEditable's XPath expression
        return !element.findElements(By.xpath("self::node()[" +
            "ancestor-or-self::*[@contenteditable=\"\" or @contenteditable=\"true\" or @contenteditable=\"false\"][1]/@contenteditable = \"\"" +
            " or " +
            "ancestor-or-self::*[@contenteditable=\"\" or @contenteditable=\"true\" or @contenteditable=\"false\"][1]/@contenteditable = \"true\"" +
        "]")).isEmpty();
    }


}
