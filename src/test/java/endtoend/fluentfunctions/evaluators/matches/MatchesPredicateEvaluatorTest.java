package endtoend.fluentfunctions.evaluators.matches;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.function.Predicate;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import io.github.seleniumquery.fluentfunctions.assertthat.SeleniumQueryAssertionError;
import io.github.seleniumquery.fluentfunctions.waituntil.SeleniumQueryTimeoutException;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

public class MatchesPredicateEvaluatorTest {

    @ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    private final Predicate<String> aaa = text -> text.contains("aaa");
    private final Predicate<String> zzz = text -> text.contains("zzz");

    @Test
    public void matches__predicate_success() {
        assertEquals("aaa bbb", $("div").waitUntil().text().matches(aaa).then().text());
        assertEquals("aaa bbb", $("div").assertThat().text().matches(aaa).then().text());
    }

    @Test
    public void matches__predicate_fails() {
        try {
            $("div").waitUntil(100).text().matches(zzz);
            fail();
        } catch (SeleniumQueryTimeoutException e) {
            assertEquals("Timeout while waiting for $(\"div\").waitUntil().text().matches(<predicate function>).\n\n" +
                "expected: <text() to satisfy predicate function>\n" +
                "but: <last text() was \"aaa bbb\">", e.getMessage());
        }
    }

    @Test
    public void matches__predicate_fails_assertThat() {
        try {
            $("div").assertThat().text().matches(zzz);
            fail();
        } catch (SeleniumQueryAssertionError e) {
            assertEquals("Failed assertion $(\"div\").assertThat().text().matches(<predicate function>).\n\n" +
                "expected: <text() to satisfy predicate function>\n" +
                "but: <text() was \"aaa bbb\">", e.getMessage());
        }
    }

}
