package endtoend.fluentfunctions.evaluators.matches;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.junit.Assert.*;

import java.util.regex.Pattern;

import org.hamcrest.Matchers;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import io.github.seleniumquery.fluentfunctions.assertthat.SeleniumQueryAssertionError;
import io.github.seleniumquery.fluentfunctions.waituntil.SeleniumQueryTimeoutException;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

public class MatchesPatternEvaluatorTest {

    @ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    private final Pattern abCaseInsensitive = Pattern.compile("A{3} B{3}", Pattern.CASE_INSENSITIVE);
    private final Pattern zzzPattern = Pattern.compile("Z{3}");


    @Test
    public void matches__pattern_success() {
        assertEquals("aaa bbb", $("div").waitUntil().text().matches(abCaseInsensitive).then().text());
        assertEquals("aaa bbb", $("div").assertThat().text().matches(abCaseInsensitive).then().text());
    }

    @Test
    public void matches__pattern_fails() {
        try {
            $("div").waitUntil(100).text().matches(zzzPattern);
            fail();
        } catch (SeleniumQueryTimeoutException e) {
            assertEquals("Timeout while waiting for $(\"div\") to .waitUntil().text().matches(\"Z{3}\")", e.getMessage());
        }
    }

    @Test
    public void matches__pattern_fails_assertThat() {
        try {
            $("div").assertThat().text().matches(zzzPattern);
            fail();
        } catch (SeleniumQueryAssertionError e) {
            assertEquals("Failed assertion: $(\"div\").assertThat().text().matches(\"Z{3}\")", e.getMessage());
        }
    }

}
