package testinfrastructure;

import io.github.seleniumquery.by.SeleniumQueryBy;

import java.lang.reflect.Field;

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
 */
public class SecondGenSelectorSystemDetector {

    private static final Boolean isSecondGenSelectorSystem = figureOutIfItsSecondGenSelectorSystem();

    public static void failIfSecondGenSelectorSystem() {
        if (isSecondGenSelectorSystem) {
            fail();
        }
    }

    public static void failIfFirstGenSelectorSystem(IllegalArgumentException e) {
        if (!isSecondGenSelectorSystem) {
            throw new RuntimeException(e);
        }
    }

    private static boolean figureOutIfItsSecondGenSelectorSystem() {
        try {
            Field element_finder = SeleniumQueryBy.class.getDeclaredField("ELEMENT_FINDER");
            element_finder.setAccessible(true);
            return element_finder.get(null).getClass().getName().contains("secondgen");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
