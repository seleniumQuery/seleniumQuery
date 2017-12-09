/*
 * Copyright (c) 2017 seleniumQuery authors
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
import java.util.function.Consumer;

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


    public static DriverInstantiator PHANTOMJS = create("PhantomJS", PhantomJSOnly.class,                                $ -> $.driver().usePhantomJS().autoDriverDownload());
    public static DriverInstantiator FIREFOX = create("Firefox", FirefoxOnly.class,                                      $ -> $.driver().useFirefox().autoDriverDownload());
    public static DriverInstantiator IE = create("IE", IEOnly.class,                                                     $ -> $.driver().useInternetExplorer().autoDriverDownload());
    public static DriverInstantiator CHROME = create("Chrome", ChromeOnly.class,                                         $ -> $.driver().useChrome().autoDriverDownload());
    public static DriverInstantiator CHROME_HEADLESS = create("Chrome Headless", ChromeOnly.class,                       $ -> $.driver().useChrome().autoDriverDownload().headless());
    public static DriverInstantiator HTMLUNIT_CHROME_JS_ON = create("HtmlUnit (Chrome) - JS ON", HtmlUnitOnly.class,     $ -> $.driver().useHtmlUnit().emulatingChrome());
    public static DriverInstantiator HTMLUNIT_CHROME_JS_OFF = create("HtmlUnit (Chrome) - JS OFF", HtmlUnitOnly.class,   $ -> $.driver().useHtmlUnit().emulatingChrome().withoutJavaScript());
    public static DriverInstantiator HTMLUNIT_FIREFOX_JS_ON = create("HtmlUnit (Firefox) - JS ON", HtmlUnitOnly.class,   $ -> $.driver().useHtmlUnit().emulatingFirefox());
    public static DriverInstantiator HTMLUNIT_FIREFOX_JS_OFF = create("HtmlUnit (Firefox) - JS OFF", HtmlUnitOnly.class, $ -> $.driver().useHtmlUnit().emulatingFirefox().withoutJavaScript());
    public static DriverInstantiator HTMLUNIT_IE_JS_ON = create("HtmlUnit (IE) - JS ON", HtmlUnitOnly.class,             $ -> $.driver().useHtmlUnit().emulatingInternetExplorer());
    public static DriverInstantiator HTMLUNIT_IE_JS_OFF = create("HtmlUnit (IE) - JS OFF", HtmlUnitOnly.class,           $ -> $.driver().useHtmlUnit().emulatingInternetExplorer().withoutJavaScript());


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

    private static DriverInstantiator create(String driverDescription, Class<? extends Annotation> driverOnlyAnnotation, Consumer<BrowserFunctions> driverCreator) {
        return new DriverInstantiator(driverDescription, driverOnlyAnnotation) {
            @Override
            public void instantiateDriver(BrowserFunctions $) {
                driverCreator.accept($);
            }
        };
    }

}
