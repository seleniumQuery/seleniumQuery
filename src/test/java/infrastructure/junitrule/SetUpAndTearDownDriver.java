package infrastructure.junitrule;

import infrastructure.junitrule.statementrunner.RunStatementInEveryDriver;
import infrastructure.junitrule.statementrunner.RunStatementInGivenDriver;
import infrastructure.junitrule.statementrunner.StatementRunner;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.io.File;

import static io.github.seleniumquery.SeleniumQuery.$;

public class SetUpAndTearDownDriver implements TestRule {

	private static final String TEST_SRC_FOLDER = "src/test/java/";

	private static final DriverToRunTestsIn driverToRunTestsIn = DriverToRunTestsIn.ALL_DRIVERS;

	private final Class<?> htmlTestUrlClass;

	public SetUpAndTearDownDriver(Class<?> htmlTestUrlClass) {
		this.htmlTestUrlClass = htmlTestUrlClass;
	}

	private void openTestPage() {
		$.browser.openUrl(htmlTestFileUrl(htmlTestUrlClass));
	}

	private static String htmlTestFileUrl(Class<?> clazz) {
		String classFullName = clazz.getName();
		String classPath = classFullName.replace('.', '/');
		String htmlPath = TEST_SRC_FOLDER + classPath + ".html";
		return new File(htmlPath).toURI().toString();
	}

	@Override
	public Statement apply(final Statement base, Description description) {
		StatementRunner statementRunner = new StatementRunner() {
			@Override
			public void before() {
				openTestPage();
			}

			@Override
			public void evaluate() throws Throwable {
				base.evaluate();
			}

			@Override
			public void after() { }
		};
		if (driverToRunTestsIn == DriverToRunTestsIn.ALL_DRIVERS) {
			return new RunStatementInEveryDriver(statementRunner);
		}
		return new RunStatementInGivenDriver(statementRunner);
	}
	
}

enum DriverToRunTestsIn {
	ALL_DRIVERS, GIVEN_DRIVER
}