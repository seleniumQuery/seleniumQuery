package infrastructure.junitrule;

import infrastructure.junitrule.statementrunner.RunStatementInEveryDriver;
import infrastructure.junitrule.statementrunner.RunStatementInGivenDriver;
import infrastructure.junitrule.statementrunner.StatementRunner;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import static io.github.seleniumquery.SeleniumQuery.$;

public class SetUpAndTearDownDriver implements TestRule {

	private static final DriverToRunTestsIn driverToRunTestsIn = DriverToRunTestsIn.ALL_DRIVERS;

	private final Class<?> htmlTestUrlClass;

	public SetUpAndTearDownDriver(Class<?> htmlTestUrlClass) {
		this.htmlTestUrlClass = htmlTestUrlClass;
	}

	private void openTestPage() {
		$.url(IntegrationTestUtils.htmlTestFileUrl(htmlTestUrlClass));
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
		return new RunStatementInEveryDriver(driverToRunTestsIn, statementRunner);
	}
	
}