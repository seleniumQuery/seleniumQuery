package infrastructure.junitrule;

import infrastructure.junitrule.statementrunner.StatementRunner;
import infrastructure.junitrule.statementrunner.RunStatementInEveryDriver;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.junit.Assert.fail;

public class SetUpAndTearDownEveryDriver extends SetUpAndTearDownBase implements TestRule {

	public SetUpAndTearDownEveryDriver(Class<?> htmlTestUrlClass) {
		super(htmlTestUrlClass);
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
		return new RunStatementInEveryDriver(statementRunner);
	}

}