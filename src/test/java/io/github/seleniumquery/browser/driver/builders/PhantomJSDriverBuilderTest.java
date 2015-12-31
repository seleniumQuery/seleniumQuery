package io.github.seleniumquery.browser.driver.builders;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static io.github.seleniumquery.browser.driver.builders.PhantomJSDriverBuilder.PHANTOMJS_EXECUTABLE_SYSTEM_PROPERTY;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class PhantomJSDriverBuilderTest {

    private static final String EXECUTABLE_THAT_EXISTS_IN_CLASSPATH = "executable-that-exists-in-classpath";
    private static final String EXECUTABLE_THAT_DOESNT_EXIST_IN_CLASSPATH = "executable-that-DOESN'T-exist-in-classpath";

    private static final boolean IS_WINDOWS_OS = true;
    private static final boolean IS_LINUX_OS = false;

    @Before
    public void setUp() {
        resetPhantomJSExecutablePathSystemProperty();
    }

    @After
    public void tearDown() {
        resetPhantomJSExecutablePathSystemProperty();
    }

    private void resetPhantomJSExecutablePathSystemProperty() {
        System.clearProperty(PHANTOMJS_EXECUTABLE_SYSTEM_PROPERTY);
    }

    @Test
    public void underWindows__propertyShouldBeSet__ifWindowsExecutableFoundInClasspath() {
        // given
        PhantomJSDriverBuilder driverBuilder = new PhantomJSDriverBuilder(IS_WINDOWS_OS, EXECUTABLE_THAT_EXISTS_IN_CLASSPATH, EXECUTABLE_THAT_DOESNT_EXIST_IN_CLASSPATH);
        // when
        driverBuilder.configurePhantomJsExecutablePath();
        // then
        assertPhantomJSExecutablePropertyPointsToExecutableInClasspath();
    }

    private void assertPhantomJSExecutablePropertyPointsToExecutableInClasspath() {
        String property = System.getProperty(PHANTOMJS_EXECUTABLE_SYSTEM_PROPERTY);
        assertThat(property, endsWith("seleniumQuery/target/test-classes/"+EXECUTABLE_THAT_EXISTS_IN_CLASSPATH));
    }

    @Test
    public void underLinux__propertyShouldBeSet__ifWindowsExecutableFoundInClasspath() {
        // given
        PhantomJSDriverBuilder driverBuilder = new PhantomJSDriverBuilder(IS_LINUX_OS, EXECUTABLE_THAT_DOESNT_EXIST_IN_CLASSPATH, EXECUTABLE_THAT_EXISTS_IN_CLASSPATH);
        // when
        driverBuilder.configurePhantomJsExecutablePath();
        // then
        assertPhantomJSExecutablePropertyPointsToExecutableInClasspath();
    }

    @Test
    public void underWindows__propertyShouldNotBeSet__whenWindowsExecutableIsNotFound__evenIfItFindsLinuxExecutable() {
        // given
        PhantomJSDriverBuilder driverBuilder = new PhantomJSDriverBuilder(IS_WINDOWS_OS, EXECUTABLE_THAT_DOESNT_EXIST_IN_CLASSPATH, EXECUTABLE_THAT_EXISTS_IN_CLASSPATH);
        // when
        driverBuilder.configurePhantomJsExecutablePath();
        // then
        String property = System.getProperty(PHANTOMJS_EXECUTABLE_SYSTEM_PROPERTY);
        assertThat(property, is(nullValue()));
    }

    @Test
    public void underLinux__propertyShouldNotBeSet__whenLinuxExecutableIsNotFound__evenIfItFindsWindowsExecutable() {
        // given
        PhantomJSDriverBuilder driverBuilder = new PhantomJSDriverBuilder(IS_LINUX_OS, EXECUTABLE_THAT_EXISTS_IN_CLASSPATH, EXECUTABLE_THAT_DOESNT_EXIST_IN_CLASSPATH);
        // when
        driverBuilder.configurePhantomJsExecutablePath();
        // then
        String property = System.getProperty(PHANTOMJS_EXECUTABLE_SYSTEM_PROPERTY);
        assertThat(property, is(nullValue()));
    }

    @Test
    public void underWindows__propertyShouldBeSet__whenCustomPathIsProvided__andExecutableExistsThere() {
        verifyCustomPathIsSetUnderOs(IS_WINDOWS_OS);
    }

    @Test
    public void underLinux__propertyShouldBeSet__whenCustomPathIsProvided__andExecutableExistsThere() {
        verifyCustomPathIsSetUnderOs(IS_LINUX_OS);
    }

    private void verifyCustomPathIsSetUnderOs(boolean isWindowsOs) {
        // given
        PhantomJSDriverBuilder driverBuilder = new PhantomJSDriverBuilder(isWindowsOs, EXECUTABLE_THAT_DOESNT_EXIST_IN_CLASSPATH, EXECUTABLE_THAT_DOESNT_EXIST_IN_CLASSPATH);
        String customPathToPhantomJs = "src\\test\\resources\\" + EXECUTABLE_THAT_EXISTS_IN_CLASSPATH;
        driverBuilder.withPathToPhantomJS(customPathToPhantomJs);
        // when
        driverBuilder.configurePhantomJsExecutablePath();
        // then
        String property = System.getProperty(PHANTOMJS_EXECUTABLE_SYSTEM_PROPERTY);
        assertThat(property, endsWith(customPathToPhantomJs));
    }

}