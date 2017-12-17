package testinfrastructure.junitrule.config;

import testinfrastructure.testutils.EnvironmentTestUtils;

public class EndToEndTestConfig {

    public static DriverToRunTestsIn whatDriversShouldTestsRun() {
        if (EnvironmentTestUtils.isRunningAtTravis()) {
            banner("travis");
            return DriverToRunTestsIn.PHANTOMJS;
        }
        if (EnvironmentTestUtils.isRunningAtAppveyor()) {
            banner("appveyor");
            return DriverToRunTestsIn.FIREFOX;
        }
        if (EnvironmentTestUtils.isRunningAtCircleCi()) {
            banner("circleci");
            return DriverToRunTestsIn.HTMLUNIT_ALL_JS_OFF_ONLY;
        }
        if (EnvironmentTestUtils.isRunningAtShippable()) {
            banner("shippable");
            return DriverToRunTestsIn.CHROME;
        }
        if (EnvironmentTestUtils.isRunningAtCodeShip()) {
            banner("codeship");
            // will also run DriverToRunTestsIn.REMOTE if last commit message contains [run sauce]
            return DriverToRunTestsIn.HTMLUNIT_CHROME_JS_ON_ONLY;
        }
        if (EnvironmentTestUtils.isRunningAtWercker()) {
            banner("wercker");
            return DriverToRunTestsIn.HTMLUNIT_ALL_JS_ON_ONLY;
        }
        if (EnvironmentTestUtils.isRunningAtContinuousIntegrationServer()) {
            banner("any other CI");
            return DriverToRunTestsIn.HEADLESS_DRIVERS_JS_ON_AND_OFF;
        }
        return DriverToRunTestsIn.HEADLESS_DRIVERS_JS_ON_ONLY;
    }

    private static void banner(String environment) {
        System.out.println("###############################################################################################");
        System.out.println("###############################################################################################");
        System.out.println("### Environment detected: " + environment);
        System.out.println("###############################################################################################");
        System.out.println("###############################################################################################");
    }

}
