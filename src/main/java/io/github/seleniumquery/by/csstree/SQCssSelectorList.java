package io.github.seleniumquery.by.csstree;

import io.github.seleniumquery.by.parser.parsetree.selector.SQCssSelector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class SQCssSelectorList implements Iterable<SQCssSelector> {

    private List<SQCssSelector> sqCssSelectors;

    public SQCssSelectorList(List<SQCssSelector> sqCssSelectors) {
        this.sqCssSelectors = Collections.unmodifiableList(new ArrayList<SQCssSelector>(sqCssSelectors));
    }

    public SQCssSelector selector(int i) {
        return sqCssSelectors.get(i);
    }

    public SQCssSelector firstSelector() {
        return selector(0);
    }

    @Override
    public Iterator<SQCssSelector> iterator() {
        return sqCssSelectors.iterator();
    }

}