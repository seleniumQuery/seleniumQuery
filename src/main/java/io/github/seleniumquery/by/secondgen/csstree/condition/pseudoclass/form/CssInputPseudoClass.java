/*
 * Copyright (c) 2015 seleniumQuery authors
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

package io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.form;

import static org.apache.commons.lang3.StringUtils.join;

import org.openqa.selenium.WebDriver;

import io.github.seleniumquery.by.firstgen.css.pseudoclasses.InputPseudoClass;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.CssPseudoClassCondition;
import io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass.finderfactorystrategy.NeverNativelySupportedPseudoClass;
import io.github.seleniumquery.by.secondgen.finder.XPathAndFilterFinder;

/**
 * :input
 * https://api.jquery.com/input-selector/
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class CssInputPseudoClass implements CssPseudoClassCondition {

    public static final String PSEUDO = "input";
    public static final String INPUT_TAGS_XPATH = "(self::" + join(InputPseudoClass.FORM_ELEMENT_TAGS, " or self::") + ")";

    public NeverNativelySupportedPseudoClass inputPseudoClassFinderFactoryStrategy = new NeverNativelySupportedPseudoClass() {
        @Override
        public XPathAndFilterFinder toXPath(WebDriver webDriver) {
            return XPathAndFilterFinder.pureXPath(INPUT_TAGS_XPATH);
        }
    };

    @Override
    public NeverNativelySupportedPseudoClass getElementFinderFactoryStrategy() {
        return inputPseudoClassFinderFactoryStrategy;
    }

}
