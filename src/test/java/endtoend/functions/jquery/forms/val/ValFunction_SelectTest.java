package endtoend.functions.jquery.forms.val;

import static io.github.seleniumquery.SeleniumQuery.$;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import testinfrastructure.junitrule.SetUpAndTearDownDriver;

public class ValFunction_SelectTest {

    @ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

    @Test
    public void val_singleSelect() {
        $("#single").assertThat().val().isEqualTo("SAAA");
    }

    @Test
    public void val_singleSelect_nonDefault() {
        $("#single-non-default").assertThat().val().isEqualTo("S2CCC");
    }

    @Test
    public void val_multiSelect() {
        $("#multi").assertThat().val().isEqualTo("MBBB,MDDD");
    }

    @Test
    public void val_multiSelect_no_selection() {
        $("#multi-no-selection").assertThat().val().isEqualTo("");
    }

}
