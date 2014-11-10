package infrastructure.junitrule.statementrunner;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.junit.Assert.fail;

public abstract class StatementRunner {

    private String failed = "";
    private Throwable firstFailure;

    public abstract void before();
    public abstract void evaluate() throws Throwable;
    public abstract void after();

    public void executeMethodForDriver(String driver) {
        before();
        try {
            evaluate();
        } catch (Throwable t) {
            if (this.firstFailure == null) {
                this.firstFailure = t;
            }
            failed += driver + " ";
            System.err.println("Test failed!"+t.getMessage());
        } finally {
            after();
        }
    }

    public void reportFailures() throws Throwable {
        if (!failed.isEmpty()) {
            if (this.firstFailure == null) {
                fail("There are test failures in the drivers: "+failed);
            } else {
                throw this.firstFailure;
            }
        }
    }

}