package infrastructure.junitrule;

import org.junit.runners.model.Statement;

import static io.github.seleniumquery.SeleniumQuery.$;

class TestMethodsRunner {

	private String failed = "";
	private Throwable firstFailure;
	private Statement base;
	private String url;

	public TestMethodsRunner(Statement base, String url) {
		this.base = base;
		this.url = url;
	}

	public void executeMethodForDriver(String driver) {
		System.out.println("   @## >>> Running on "+driver);
		$.url(url); // this wont be needed when everyone use this both as @Rule and @ClassRule
		try {
			base.evaluate();
		} catch (Throwable t) {
			if (this.firstFailure == null) {
				this.firstFailure = t;
			}
			failed += driver + " ";
			System.out.println("   @## %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% FAILED on "+driver+"! -> "+t.getMessage());
		}
		System.out.println("   @## <<< Done on "+driver);
	}

	public void reportFailures() throws Throwable {
		if (this.firstFailure != null) {
			throw new AssertionError("There are test failures in some drivers: "+failed, this.firstFailure);
		}
	}

}