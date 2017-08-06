/*
 * Copyright (c) 2016 seleniumQuery authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package testinfrastructure.junitrule.config;

import static java.util.Arrays.asList;

import java.lang.annotation.Annotation;
import java.util.List;

import org.openqa.selenium.chrome.ChromeOptions;

import io.github.seleniumquery.browser.BrowserFunctions;
import testinfrastructure.junitrule.annotation.ChromeOnly;
import testinfrastructure.junitrule.annotation.EdgeOnly;
import testinfrastructure.junitrule.annotation.FirefoxOnly;
import testinfrastructure.junitrule.annotation.HtmlUnitOnly;
import testinfrastructure.junitrule.annotation.IEOnly;
import testinfrastructure.junitrule.annotation.OperaOnly;
import testinfrastructure.junitrule.annotation.PhantomJSOnly;
import testinfrastructure.junitrule.annotation.SafariOnly;

@SuppressWarnings("deprecation")
public abstract class DriverInstantiator {

    private static final List<Class<? extends Annotation>> DRIVER_ONLY_ANNOTATIONS = asList(ChromeOnly.class, EdgeOnly.class,
        FirefoxOnly.class, HtmlUnitOnly.class, IEOnly.class, OperaOnly.class, PhantomJSOnly.class, SafariOnly.class);

    private String driverDescription;
    private final Class<? extends Annotation> driverAnnotation;

    DriverInstantiator(String driverDescription, Class<? extends Annotation> driverOnlyAnnotation) {
        this.driverDescription = driverDescription;
        this.driverAnnotation = driverOnlyAnnotation;
    }
    public String getDriverDescription() { return driverDescription; }

    public abstract void instantiateDriver(BrowserFunctions browser);

    public static DriverInstantiator PHANTOMJS = new DriverInstantiator("PhantomJS", PhantomJSOnly.class) {
        @Override public void instantiateDriver(BrowserFunctions $) {
            $.driver().usePhantomJS();
        }
    };
    public static DriverInstantiator FIREFOX_JS_ON = new DriverInstantiator("Firefox - JS ON", FirefoxOnly.class) {
        @Override public void instantiateDriver(BrowserFunctions $) {
            $.driver().useFirefox();
        }
    };
    public static DriverInstantiator FIREFOX_JS_OFF = new DriverInstantiator("Firefox - JS OFF", FirefoxOnly.class) {
        @Override public void instantiateDriver(BrowserFunctions $) {
            $.driver().useFirefox().withoutJavaScript();
        }
    };
    public static DriverInstantiator IE = new DriverInstantiator("IE", IEOnly.class) {
        @Override public void instantiateDriver(BrowserFunctions $) {
            $.driver().useInternetExplorer();
        }
    };
    public static DriverInstantiator CHROME = new DriverInstantiator("Chrome", ChromeOnly.class) {
        @Override public void instantiateDriver(BrowserFunctions $) {
            $.driver().useChrome();
        }
    };
    public static DriverInstantiator CHROME_HEADLESS = new DriverInstantiator("Chrome Headless", ChromeOnly.class) {
        @Override public void instantiateDriver(BrowserFunctions $) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("headless");
            chromeOptions.addArguments("disable-gpu");
            chromeOptions.addArguments("no-sandbox");
            $.driver().useChrome().withOptions(chromeOptions);
        }
    };
    public static DriverInstantiator HTMLUNIT_CHROME_JS_ON = new DriverInstantiator("HtmlUnit (Chrome) - JS ON", HtmlUnitOnly.class) {
        @Override public void instantiateDriver(BrowserFunctions $) {
            $.driver().useHtmlUnit().emulatingChrome();
        }
    };
    public static DriverInstantiator HTMLUNIT_CHROME_JS_OFF = new DriverInstantiator("HtmlUnit (Chrome) - JS OFF", HtmlUnitOnly.class) {
        @Override public void instantiateDriver(BrowserFunctions $) {
            $.driver().useHtmlUnit().emulatingChrome().withoutJavaScript();
        }
    };
    public static DriverInstantiator HTMLUNIT_FIREFOX_JS_ON = new DriverInstantiator("HtmlUnit (Firefox) - JS ON", HtmlUnitOnly.class) {
        @Override public void instantiateDriver(BrowserFunctions $) {
            $.driver().useHtmlUnit().emulatingFirefox();
        }
    };
    public static DriverInstantiator HTMLUNIT_FIREFOX_JS_OFF = new DriverInstantiator("HtmlUnit (Firefox) - JS OFF", HtmlUnitOnly.class) {
        @Override public void instantiateDriver(BrowserFunctions $) {
            $.driver().useHtmlUnit().emulatingFirefox().withoutJavaScript();
        }
    };
    public static DriverInstantiator HTMLUNIT_IE_JS_ON = new DriverInstantiator("HtmlUnit (IE) - JS ON", HtmlUnitOnly.class)  { @Override public void instantiateDriver(BrowserFunctions $) { $.driver().useHtmlUnit().emulatingInternetExplorer(); } };
    public static DriverInstantiator HTMLUNIT_IE_JS_OFF = new DriverInstantiator("HtmlUnit (IE) - JS OFF", HtmlUnitOnly.class) { @Override public void instantiateDriver(BrowserFunctions $) { $.driver().useHtmlUnit().emulatingInternetExplorer().withoutJavaScript(); } };

    public boolean shouldSkipTestClass(Class<?> testClass) {
        return classHasAtLeastOneDriverAnnotationButNot(testClass, this.driverAnnotation);
    }

    private static boolean classHasAtLeastOneDriverAnnotationButNot(Class<?> testClass, Class<? extends Annotation> annotationClass) {
        return classHasAtLeastOneDriverAnnotation(testClass) && !testClass.isAnnotationPresent(annotationClass);
    }

    private static boolean classHasAtLeastOneDriverAnnotation(Class<?> testClass) {
        if (testClass.getAnnotations().length > 0) {
            for (Class<? extends Annotation> annotation : DRIVER_ONLY_ANNOTATIONS) {
                if (testClass.isAnnotationPresent(annotation)) {
                    return true;
                }
            }
        }
        return  false;
    }

}
