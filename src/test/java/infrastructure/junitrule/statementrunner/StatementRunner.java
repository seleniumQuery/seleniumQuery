package infrastructure.junitrule.statementrunner;

public abstract class StatementRunner {

    private String failed = "";
    private Throwable firstFailure;

    public abstract void before();
    public abstract void evaluate() throws Throwable;
    public abstract void after();

    public void executeMethodForDriver(String driver) {
        System.out.println("   @## >>> Running on "+driver);
        before();
        try {
            evaluate();
        } catch (Throwable t) {
            if (this.firstFailure == null) {
                this.firstFailure = t;
            }
            failed += driver + " ";
            System.out.println("   @## %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% FAILED on "+driver+"! -> "+t.getMessage());
        } finally {
            after();
        }
        System.out.println("   @## <<< Done on "+driver);
    }

    public void reportFailures() throws Throwable {
        if (!failed.isEmpty()) {
            throw new AssertionError("There are test failures in some drivers: "+failed, this.firstFailure);
        }
    }

}