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

package io.github.seleniumquery.by.csstree.condition.pseudoclass.form;

import io.github.seleniumquery.by.DriverVersionUtils;
import io.github.seleniumquery.by.css.pseudoclasses.DisabledPseudoClass;
import io.github.seleniumquery.by.csstree.condition.SQCssConditionImplementedLocators;
import io.github.seleniumquery.by.csstree.condition.pseudoclass.SQCssPseudoClassCondition;
import io.github.seleniumquery.by.locator.SQLocator;
import io.github.seleniumquery.by.locator.SQLocatorFactory;
import io.github.seleniumquery.by.locator.SQLocatorUtils;

public class SQCssEnabledPseudoClass extends SQCssPseudoClassCondition implements SQCssConditionImplementedLocators {

    public static final String PSEUDO = "enabled";
    public static final String ENABLED_PSEUDO = ":" + PSEUDO;

    @Override
    public SQLocator toSQLocator(SQLocator leftLocator) {
        if (!supportsNatively(leftLocator)) {
            return SQLocatorFactory.createPureXPathOnly(leftLocator, mergeXPath(leftLocator));
        }
        return new SQLocator(mergeCss(leftLocator), mergeXPath(leftLocator), leftLocator);
    }

    private boolean supportsNatively(SQLocator leftLocator) {
        return DriverVersionUtils.getInstance().hasNativeSupportForPseudo(leftLocator.getWebDriver(), ENABLED_PSEUDO);
    }

    private String mergeCss(SQLocator leftLocator) {
        return SQLocatorUtils.cssMerge(leftLocator.getCssSelector(), toCSS());
    }

    private String toCSS() {
        return ENABLED_PSEUDO;
    }

    private String mergeXPath(SQLocator leftLocator) {
        return SQLocatorUtils.conditionalSimpleXPathMerge(leftLocator.getXPathExpression(), toXPath());
    }

    private String toXPath() {
        return "(not(@disabled) and " + DisabledPseudoClass.DISABLEABLE_TAGS_XPATH + ")";
    }

}