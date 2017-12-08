package endtoend.fluentfunctions.evaluators.matches;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.regex.Pattern;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import io.github.seleniumquery.fluentfunctions.assertthat.SeleniumQueryAssertionError;
import io.github.seleniumquery.fluentfunctions.waituntil.SeleniumQueryTimeoutException;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

public class MatchesPatternEvaluatorTest {

    @ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    private final Pattern abCaseInsensitive = Pattern.compile("A{3} B{3}", Pattern.CASE_INSENSITIVE);
    private final Pattern zzzPattern = Pattern.compile("Z{3}");

    @Test
    public void matches__pattern_waitUntil_success() {
        assertEquals("aaa bbb", $("div").waitUntil().text().matches(abCaseInsensitive).then().text());
    }

    @Test
    public void matches__pattern_assertThat_success() {
        assertEquals("aaa bbb", $("div").assertThat().text().matches(abCaseInsensitive).then().text());
    }

    @Test
    public void matches__pattern_fails_waitUntil() {
        try {
            $("div").waitUntil(100).text().matches(zzzPattern);
            fail();
        } catch (SeleniumQueryTimeoutException e) {
            assertEquals("Timeout while waiting for $(\"div\").waitUntil().text().matches(\"Z{3}\").\n\n" +
                "expected: <text() to match Pattern \"Z{3}\">\n" +
                "but: <last text() was \"aaa bbb\">", e.getMessage());
        }
    }

    @Test
    public void matches__pattern_fails_assertThat() {
        try {
            $("div").assertThat().text().matches(zzzPattern);
            fail();
        } catch (SeleniumQueryAssertionError e) {
            assertEquals("Failed assertion $(\"div\").assertThat().text().matches(\"Z{3}\").\n\n" +
                "expected: <text() to match Pattern \"Z{3}\">\n" +
                "but: <text() was \"aaa bbb\">", e.getMessage());
        }
    }

    @Test
    public void matches__NOT_pattern_fails_waitUntil() {
        try {
            $("div").waitUntil(100).text().not().matches(abCaseInsensitive);
            fail();
        } catch (SeleniumQueryTimeoutException e) {
            assertEquals("Timeout while waiting for $(\"div\").waitUntil().text().not().matches(\"A{3} B{3}\").\n\n" +
                "expected: <text() not to match Pattern \"A{3} B{3}\">\n" +
                "but: <last text() was \"aaa bbb\">", e.getMessage());
        }
    }

    @Test
    public void matches__NOT_pattern_fails_assertThat() {
        try {
            $("div").assertThat().text().not().matches(abCaseInsensitive);
            fail();
        } catch (SeleniumQueryAssertionError e) {
            assertEquals("Failed assertion $(\"div\").assertThat().text().not().matches(\"A{3} B{3}\").\n\n" +
                "expected: <text() not to match Pattern \"A{3} B{3}\">\n" +
                "but: <text() was \"aaa bbb\">", e.getMessage());
        }
    }

}
