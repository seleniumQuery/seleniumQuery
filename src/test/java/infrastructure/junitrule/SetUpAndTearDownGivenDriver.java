package infrastructure.junitrule;

import infrastructure.junitrule.statementrunner.RunStatementInGivenDriver;
import infrastructure.junitrule.statementrunner.StatementRunner;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class SetUpAndTearDownGivenDriver extends SetUpAndTearDownBase implements TestRule {

	public SetUpAndTearDownGivenDriver(Class<?> htmlTestUrlClass) {
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
		return new RunStatementInGivenDriver(statementRunner);
	}
	
}