package endtoend.fluentfunctions.evaluators.matches;

import static endtoend.fluentfunctions.evaluators.EvaluatorsExceptionTestUtils.assertThrowsAssertionError;
import static endtoend.fluentfunctions.evaluators.EvaluatorsExceptionTestUtils.assertThrowsTimeoutException;
import static io.github.seleniumquery.SeleniumQuery.$;
import static org.junit.Assert.assertEquals;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import testinfrastructure.junitrule.SetUpAndTearDownDriver;

@SuppressWarnings("FieldCanBeLocal")
public class MatchesStringRegexEvaluatorTest {

    @ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    private final String aaa = "a{3} b{3}";
    private final String AAA = "A{3} B{3}";

    @Test
    public void matches__predicate_success() {
        assertEquals("aaa bbb", $("input").waitUntil().attr("value").matches(aaa).then().attr("value"));
        assertEquals("aaa bbb", $("input").assertThat().attr("value").matches(aaa).then().attr("value"));
    }

    @Test
    public void matches__predicate_fails() {
        assertThrowsTimeoutException(
            __ ->
                $("input").waitUntil(100).attr("value").matches(AAA)
            ,
            "Timeout while waiting for $(\"input\").waitUntil().attr(\"value\").matches(\"A{3} B{3}\").\n\n" +
                "expected: <attr(\"value\") to match regex \"A{3} B{3}\">\n" +
                "but: <last attr(\"value\") was \"aaa bbb\">"
        );
    }

    @Test
    public void matches__predicate_fails_assertThat() {
        assertThrowsAssertionError(
            __ ->
                $("input").assertThat().attr("value").matches(AAA)
            ,
            "Failed assertion $(\"input\").assertThat().attr(\"value\").matches(\"A{3} B{3}\").\n\n" +
                "expected: <attr(\"value\") to match regex \"A{3} B{3}\">\n" +
                "but: <attr(\"value\") was \"aaa bbb\">"
        );
    }

}
