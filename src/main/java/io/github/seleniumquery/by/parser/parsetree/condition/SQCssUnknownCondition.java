package io.github.seleniumquery.by.parser.parsetree.condition;

import org.w3c.css.sac.Condition;

public class SQCssUnknownCondition extends RuntimeException {

    public SQCssUnknownCondition(Condition condition) {
        super("CSS condition " + condition.getClass().getSimpleName() + " of type " + condition.getConditionType() + " is invalid or not supported!");
    }

}