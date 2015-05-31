package testinfrastructure.junitrule;

import org.junit.*;

public class SetUpAndTearDownDriverTest {

//    @ClassRule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();
//    @Rule public SetUpAndTearDownDriver setUpAndTearDownDriverRuleInstance = setUpAndTearDownDriverRule;

    @Test @JavaScriptOnly
    public void a() throws Exception {
        System.out.println("a");
    }

    @Test
    public void b() throws Exception {
        System.out.println("b");
    }

}