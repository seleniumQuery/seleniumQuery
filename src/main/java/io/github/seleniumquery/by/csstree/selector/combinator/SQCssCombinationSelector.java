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

package io.github.seleniumquery.by.csstree.selector.combinator;

import io.github.seleniumquery.by.csstree.selector.SQCssSelector;
import io.github.seleniumquery.by.locator.SQLocator;
import org.openqa.selenium.WebDriver;

/**
 * An ABSTRACT class that is used as base implementation for all the combination selectors.
 *
 * @author acdcjunior
 * @since 0.10.0
 */
abstract class SQCssCombinationSelector implements SQCssSelector {

    private final String cssCombinator;
    private final String xPathCombinator;
    protected SQCssSelector leftSideSelector;
    protected SQCssSelector rightSideSelector;

    public SQCssCombinationSelector(String cssCombinator, String xPathCombinator,
                                    SQCssSelector leftSideSelector, SQCssSelector rightSideSelector) {
        this.cssCombinator = cssCombinator;
        this.xPathCombinator = xPathCombinator;
        this.leftSideSelector = leftSideSelector;
        this.rightSideSelector = rightSideSelector;
    }

    @Override
    public SQLocator toSQLocator(WebDriver webDriver) {
        SQLocator sqLocator = leftSideSelector.toSQLocator(webDriver);
        SQLocator directAdjacentIntermediateLocator = new SQLocator(sqLocator.getCssSelector() + this.cssCombinator,
                sqLocator.getXPathExpression() + this.xPathCombinator, sqLocator);
        return rightSideSelector.toSQLocator(directAdjacentIntermediateLocator);
    }

    @Override
    public SQLocator toSQLocator(SQLocator leftLocator) {
        throw new UnsupportedOperationException("Due to the way the CSS Tree is created by the CSS Parser, this " +
                "order of evaluation should never happen.");
    }

}