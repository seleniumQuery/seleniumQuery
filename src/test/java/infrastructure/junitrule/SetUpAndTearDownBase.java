package infrastructure.junitrule;

import java.io.File;

import static io.github.seleniumquery.SeleniumQuery.$;

abstract class SetUpAndTearDownBase {

    private static final String TEST_SRC_FOLDER = "src/test/java/";

    private final Class<?> htmlTestUrlClass;

    protected SetUpAndTearDownBase(Class<?> htmlTestUrlClass) {
        this.htmlTestUrlClass = htmlTestUrlClass;
    }

    protected void openTestPage() {
        $.browser.openUrl(htmlTestFileUrl(htmlTestUrlClass));
    }

    public static String htmlTestFileUrl(Class<?> clazz) {
        String classFullName = clazz.getName();
        String classPath = classFullName.replace('.', '/');
        String htmlPath = TEST_SRC_FOLDER + classPath + ".html";
        return new File(htmlPath).toURI().toString();
    }

}