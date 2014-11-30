package io.github.seleniumquery.functions.as;

import io.github.seleniumquery.SeleniumQueryObject;

/**
 * Plugin interface for seleniumQuery.
 *
 * @author acdcjunior
 * @author ricardo-sc
 *
 * @since 0.9.0
 */
public interface SeleniumQueryPlugin<PLUGIN> {

    PLUGIN as(SeleniumQueryObject seleniumQueryObject);

}