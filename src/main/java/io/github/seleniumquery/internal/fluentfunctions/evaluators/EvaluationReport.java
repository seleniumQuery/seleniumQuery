package io.github.seleniumquery.internal.fluentfunctions.evaluators;

public class EvaluationReport {

    private final String lastValue;
    private final boolean satisfiesConstraints;

    public EvaluationReport(String lastValue, boolean satisfiesConstraints) {
        this.lastValue = lastValue;
        this.satisfiesConstraints = satisfiesConstraints;
    }

    public String getLastValue() {
        return lastValue;
    }

    public boolean isSatisfiesConstraints() {
        return satisfiesConstraints;
    }

}
