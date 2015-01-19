package io.github.seleniumquery.by.csstree.condition;

import org.w3c.css.sac.Condition;

public class SQCssUnknownConditionException extends RuntimeException {

    public SQCssUnknownConditionException(Condition condition) {
        super("CSS condition " + condition.getClass().getSimpleName() + " (type=" + condition.getConditionType() + ") is invalid or not supported!");
    }

}