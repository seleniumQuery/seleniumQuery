/*
 * Copyright (c) 2017 seleniumQuery authors
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

package io.github.seleniumquery.by.secondgen.csstree.condition.attribute;

import io.github.seleniumquery.by.common.AttributeEvaluatorUtils;
import io.github.seleniumquery.utils.SelectorUtils;

/**
 * [attribute$=stringToEnd]
 *
 * CASE SENSITIVE! http://api.jquery.com/attribute-ends-with-selector/
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class CssEndsWithAttributeCondition extends CssAttributeConditionBase {

    public CssEndsWithAttributeCondition(AstCssEndsWithAttributeCondition astCssEndsWithAttributeCondition) {
        super(astCssEndsWithAttributeCondition);
    }

    @Override
    protected String symbol() {
        return "$=";
    }

    protected String toXPath() {
        String escapedAttributeName = AttributeEvaluatorUtils.toXPathAttribute(this.getAttributeName());
        String attrValue = this.getWantedValue();
        String escapedWantedValue = SelectorUtils.intoEscapedXPathString(this.getWantedValue());
        return "substring("+escapedAttributeName+", string-length(" + escapedAttributeName + ")-" +
                (attrValue.length() - 1) + ") = " + escapedWantedValue;
    }

}
