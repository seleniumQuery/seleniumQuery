package endtoend.fluentfunctions.evaluators.matches;

import static endtoend.fluentfunctions.evaluators.EvaluatorsExceptionTestUtils.assertThrowsAssertionError;
import static endtoend.fluentfunctions.evaluators.EvaluatorsExceptionTestUtils.assertThrowsTimeoutException;
import static io.github.seleniumquery.SeleniumQuery.$;
import static org.junit.Assert.assertEquals;

import java.util.function.Predicate;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

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
        assertThrowsTimeoutException(
            __ ->
                $("div").waitUntil(100).text().matches(zzz)
            ,
            "Timeout while waiting for $(\"div\").waitUntil().text().matches(<predicate function>).\n\n" +
                "expected: <text() to satisfy predicate function>\n" +
                "but: <last text() was \"aaa bbb\">"
        );
    }

    @Test
    public void matches__predicate_fails_assertThat() {
        assertThrowsAssertionError(
            __ ->
                $("div").assertThat().text().matches(zzz)
            ,
            "Failed assertion $(\"div\").assertThat().text().matches(<predicate function>).\n\n" +
                "expected: <text() to satisfy predicate function>\n" +
                "but: <text() was \"aaa bbb\">"
        );
    }

}
