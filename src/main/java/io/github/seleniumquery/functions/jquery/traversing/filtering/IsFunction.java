/*
 * Copyright (c) 2016 seleniumQuery authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.seleniumquery.functions.jquery.traversing.filtering;

import io.github.seleniumquery.SeleniumQueryObject;
import io.github.seleniumquery.by.common.preparser.ArgumentMap;
import io.github.seleniumquery.by.common.preparser.CssParsedSelectorList;
import io.github.seleniumquery.by.common.preparser.CssSelectorParser;
import io.github.seleniumquery.by.firstgen.css.CssSelector;
import io.github.seleniumquery.by.firstgen.css.CssSelectorFactory;
import io.github.seleniumquery.by.firstgen.xpath.component.TagComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.css.sac.Selector;
import org.w3c.css.sac.SelectorList;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * $("selector").is("selector")
 *
 * @author acdcjunior
 * @author ricardo-sc
 * @since 0.9.0
 */
public class IsFunction {

	private static final Pattern NOT_SQ_PATTERN = Pattern.compile("not-sq\\((\\d+)\\)");
	
	private IsFunction() {}

	public static boolean is(SeleniumQueryObject seleniumQueryObject, String selector) {
		return is(seleniumQueryObject.getWebDriver(), seleniumQueryObject.get(), selector);
	}

	public static boolean is(WebDriver driver, List<WebElement> elements, String selector) {
        CompiledSelector compiledSelector = new CompiledSelector(selector);
        return compiledSelector.is(driver, elements);
    }

    public static class CompiledSelector {

        private boolean emptySelector;
        private List<CompiledCssSelector> compiledCssSelectors;
        private boolean hasNegatedPresent;

        private class CompiledCssSelector {
            public final CssSelector<Selector, TagComponent> cssSelector;
            final boolean hasNegatedPresent;
            private final ArgumentMap argumentMap;
            final Selector parsedSimpleSelector;
            CompiledCssSelector(CssSelector<Selector, TagComponent> cssSelector, boolean hasNegatedPresent, ArgumentMap argumentMap, Selector parsedSimpleSelector) {
                this.cssSelector = cssSelector;
                this.hasNegatedPresent = hasNegatedPresent;
                this.argumentMap = argumentMap;
                this.parsedSimpleSelector = parsedSimpleSelector;
            }
        }

        public CompiledSelector(String selector) {
            if (selector == null || selector.trim().isEmpty()) {
                this.emptySelector = true;
            } else {
                compiledCssSelectors = compileCssSelector(selector);

                for (CompiledCssSelector s : compiledCssSelectors) {
                    if (s.hasNegatedPresent) {
                        this.hasNegatedPresent = true;
                    }
                }
            }
        }

        private List<CompiledCssSelector> compileCssSelector(String selector) {
            CssParsedSelectorList cssParsedSelectorList = CssSelectorParser.parseSelector(selector);
            SelectorList selectorList = cssParsedSelectorList.getSelectorList();
            ArgumentMap argumentMap = cssParsedSelectorList.getArgumentMap();

            List<CompiledCssSelector> compiledCssSelectors = new ArrayList<>();
            for (int i = 0; i < selectorList.getLength(); i++) {
                Selector parsedSimpleSelector = selectorList.item(i);
                boolean hasNegatedPresent = hasNegatedPresent(argumentMap, parsedSimpleSelector.toString());
                CssSelector<Selector, TagComponent> cssSelector = CssSelectorFactory.parsedSelectorToCssSelector(parsedSimpleSelector);
                compiledCssSelectors.add(new CompiledCssSelector(cssSelector, hasNegatedPresent, argumentMap, parsedSimpleSelector));
            }
            return compiledCssSelectors;
        }

        public boolean is(WebDriver driver, List<WebElement> elements) {
            if (emptySelector) {
                return false;
            }
            // If there is a :not(:present), then having no element is a expected status!
            if (this.hasNegatedPresent && elements.isEmpty()) {
                return true;
            }
            for (CompiledCssSelector s : compiledCssSelectors) {
                for (WebElement webElement : elements) {
                    if (s.cssSelector.is(driver, webElement, s.argumentMap, s.parsedSimpleSelector)) {
                        return true;
                    }
                }
            }
            return false;
        }

        public List<WebElement> filter(WebDriver driver, List<WebElement> elements) {
            List<WebElement> filteredElements = new ArrayList<>();
            if (emptySelector || hasNegatedPresent) {
                return filteredElements;
            }
            for (CompiledCssSelector s : compiledCssSelectors) {
                for (WebElement webElement : elements) {
                    if (s.cssSelector.is(driver, webElement, s.argumentMap, s.parsedSimpleSelector)) {
                        filteredElements.add(webElement);
                    }
                }
            }
            return filteredElements;
        }

    }

    private static boolean hasNegatedPresent(ArgumentMap argumentMap, String parsedSimpleSelector) {
		Matcher m = NOT_SQ_PATTERN.matcher(parsedSimpleSelector);
		while (m.find()) {
		    String argumentMapIndex = m.group(1);
		    String notPseudoClassContent = argumentMap.get(argumentMapIndex);
		    
			CssParsedSelectorList parsedPseudoClassContent = CssSelectorParser.parseSelector(notPseudoClassContent);
			SelectorList parsedPseudoClassContentSelectorList = parsedPseudoClassContent.getSelectorList();
			ArgumentMap parsedPseudoClassContentArgumentMap = parsedPseudoClassContent.getArgumentMap();
			for (int i = 0; i < parsedPseudoClassContentSelectorList.getLength(); i++) {
				Selector parsedPseudoClassContentSimpleSelector = parsedPseudoClassContentSelectorList.item(i);
				if (!hasNegatedPresent(parsedPseudoClassContentArgumentMap, parsedPseudoClassContentSimpleSelector.toString())) {
					return true;
				}
			}
		}
		return false;
	}
	
}