package io.github.seleniumquery.by.parser.parsetree.selector;

import org.w3c.css.sac.Selector;

/**
 * Informs right away that the given selector is not known.
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class SQCssUnknownSelectorException extends RuntimeException {

    public SQCssUnknownSelectorException(Selector selector) {
        super("CSS selector \""+selector.getClass().getSimpleName() + "\" (type="+ selector.getSelectorType() + ") is invalid or not supported!");
    }

}