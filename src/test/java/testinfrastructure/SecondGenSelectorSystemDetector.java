package testinfrastructure;

import io.github.seleniumquery.by.SeleniumQueryBy;

import java.lang.reflect.Field;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * The secondgen selector system is less lenient with selectors.
 *
 * This class will be used in the tests to detect with generation of the selector system is running.
 *
 * If it's the 1st, then it won't expect stuff like exceptions.
 * If it's the second, it will.
 *
 * At the same time, this code can't depend on the secondgen class at the compilation level, because we
 * delete them when releasing.
 *
 *
Usage:

 try {
   assertThat($("div:file").size(), is(0));
   assertThat($("span:file").size(), is(0));

   SecondGenSelectorSystemDetector.failIfSecondGenSelectorSystem();
 } catch (java.lang.IllegalArgumentException e) {
   SecondGenSelectorSystemDetector.failIfFirstGenSelectorSystem(e);
 }

 *
 *
 */
public class SecondGenSelectorSystemDetector {

    private static final Boolean isSecondGenSelectorSystem = figureOutIfItsSecondGenSelectorSystem();

    private static boolean figureOutIfItsSecondGenSelectorSystem() {
        try {
            Field element_finder = SeleniumQueryBy.class.getDeclaredField("ELEMENT_FINDER");
            element_finder.setAccessible(true);
            return element_finder.get(null).getClass().getName().contains("secondgen");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void failIfSecondGenSelectorSystem() {
        if (isSecondGenSelectorSystem) {
            fail("This test should fail when the selector system is the 2nd Gen: it it less forgiving to malformed selectors!");
        }
    }

    private static void failIfFirstGenSelectorSystem(IllegalArgumentException e) {
        if (!isSecondGenSelectorSystem) {
            throw new RuntimeException(e);
        }
    }

    private static void assertEmptyOn1stGenAndThrowsExceptionOn2ndGen(String selector) {
        try {
            assertThat($(selector).size(), is(0));
            SecondGenSelectorSystemDetector.failIfSecondGenSelectorSystem();
        } catch (java.lang.IllegalArgumentException e) {
            SecondGenSelectorSystemDetector.failIfFirstGenSelectorSystem(e);
        }
    }

    public static void assertPseudoOnDivAndSpanIsEmptyOn1stGenAndThrowsExceptionOn2ndGen(String pseudoClass) {
        assertThat("The pseudo should start with :", pseudoClass, startsWith(":"));
        SecondGenSelectorSystemDetector.assertEmptyOn1stGenAndThrowsExceptionOn2ndGen("div"+pseudoClass);
        SecondGenSelectorSystemDetector.assertEmptyOn1stGenAndThrowsExceptionOn2ndGen("span"+pseudoClass);
    }

}
