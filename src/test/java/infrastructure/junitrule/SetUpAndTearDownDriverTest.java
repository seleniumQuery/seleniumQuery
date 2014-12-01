package infrastructure.junitrule;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

public class SetUpAndTearDownDriverTest {

    @ClassRule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();
    @Rule public SetUpAndTearDownDriver setUpAndTearDownDriverRuleInstance = setUpAndTearDownDriverRule;

    @Test @JavaScriptOnly
    public void a() throws Exception {
        System.out.println("a");
    }

    @Test
    public void b() throws Exception {
        System.out.println("b");
    }

}