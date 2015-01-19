package io.github.seleniumquery.by.csstree.selector;

public class SQCssTagNameSelector implements SQCssSelector {

    private String tagName;

    public SQCssTagNameSelector(String tagName) {
        this.tagName = tagName;
    }

    public String getTagName() {
        return tagName;
    }

}