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

public class SQCssDisabledPseudoClass extends SQCssPseudoClassCondition implements SQCssConditionImplementedLocators {

    public static final String PSEUDO = "disabled";
    public static final String DISABLED_PSEUDO = ":" + PSEUDO;

    @Override
    public SQLocator toSQLocator(SQLocator leftLocator) {
        String newXPathExpression = SQLocatorUtils.conditionalSimpleXPathMerge(leftLocator.getXPathExpression(), toXPath());
        if (!supportsDisabledNatively(leftLocator)) {
            return SQLocatorFactory.createPureXPathOnly(leftLocator, newXPathExpression);
        }
        String newCssSelector = SQLocatorUtils.cssMerge(leftLocator.getCssSelector(), toCSS());
        return new SQLocator(newCssSelector, newXPathExpression, leftLocator);
    }

    private boolean supportsDisabledNatively(SQLocator leftLocator) {
        return DriverVersionUtils.getInstance().hasNativeSupportForPseudo(leftLocator.getWebDriver(), DISABLED_PSEUDO);
    }

    private String toCSS() {
        return DISABLED_PSEUDO;
    }

    private String toXPath() {
        return "(@disabled and " + DisabledPseudoClass.DISABLEABLE_TAGS_XPATH + ")";
    }

}