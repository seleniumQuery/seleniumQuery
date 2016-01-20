package testinfrastructure.testdouble;

import org.openqa.selenium.WebElement;

import static org.mockito.Mockito.doReturn;
import static testinfrastructure.testdouble.Dummies.createDummyWebElement;

public class Stubs {

    public static WebElement createStubWebElementWithTag(String tag) {
        WebElement stubWebElement = createDummyWebElement();
        doReturn(tag).when(stubWebElement).getTagName();
        return stubWebElement;
    }

}
