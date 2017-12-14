package endtoend.fluentfunctions.evaluators;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.function.Consumer;

import io.github.seleniumquery.fluentfunctions.assertthat.SeleniumQueryAssertionError;
import io.github.seleniumquery.fluentfunctions.waituntil.SeleniumQueryTimeoutException;

public class EvaluatorsExceptionTestUtils {

    public static void assertThrowsTimeoutException(Consumer<Void> functionThatThrowsException, String expectedMessage) {
        try {
            functionThatThrowsException.accept(null);
            fail("A SeleniumQueryTimeoutException was expected but not thrown.");
        } catch (SeleniumQueryTimeoutException e) {
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    public static void assertThrowsAssertionError(Consumer<Void> functionThatThrowsException, String expectedMessage) {
        try {
            functionThatThrowsException.accept(null);
            fail("A SeleniumQueryAssertionError was expected but not thrown.");
        } catch (SeleniumQueryAssertionError e) {
            assertEquals(expectedMessage, e.getMessage());
        }
    }

}
