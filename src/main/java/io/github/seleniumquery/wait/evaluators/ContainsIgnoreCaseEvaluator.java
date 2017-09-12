package io.github.seleniumquery.wait.evaluators;

import io.github.seleniumquery.wait.WaitingBehaviorModifier;
import io.github.seleniumquery.wait.getters.Getter;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ContainsIgnoreCaseEvaluator implements Evaluator<String> {
    private Getter<?> getter;

    public ContainsIgnoreCaseEvaluator(Getter<?> getter) {
        this.getter = getter;
    }

    @Override
    public boolean evaluate(WebDriver driver, List<WebElement> elements, String valueToEqual) {
        Object propertyGot = getter.get(driver, elements);

        return propertyGot != null && StringUtils.containsIgnoreCase(propertyGot.toString(), valueToEqual);
    }

    @Override
    public String stringFor(String valueToEqual, WaitingBehaviorModifier waitingBehaviorModifier) {
        return getter.toString() + waitingBehaviorModifier + ".containsIgnoreCase(\"" + valueToEqual + "\")";
    }

}
