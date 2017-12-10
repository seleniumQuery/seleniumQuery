package io.github.seleniumquery.internal.fluentfunctions.evaluators;

public class EvaluationReport<GETTERTYPE> {

    private final GETTERTYPE lastValue;
    private final boolean satisfiesConstraints;

    public EvaluationReport(GETTERTYPE lastValue, boolean satisfiesConstraints) {
        this.lastValue = lastValue;
        this.satisfiesConstraints = satisfiesConstraints;
    }

    public GETTERTYPE getLastValue() {
        return lastValue;
    }

    public boolean isSatisfiesConstraints() {
        return satisfiesConstraints;
    }

}
