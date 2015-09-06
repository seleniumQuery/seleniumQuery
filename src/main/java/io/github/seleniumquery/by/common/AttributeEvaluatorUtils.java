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

package io.github.seleniumquery.by.common;

import io.github.seleniumquery.by.SelectorUtils;
import org.w3c.css.sac.AttributeCondition;

/**
 * Utility methods for XPath attributes.
 * 
 * @author acdcjunior
 *
 * @since 0.9.0
 */
public class AttributeEvaluatorUtils {

    /**
     * Returns a XPath expression that finds the value of the {@code type} attribute and return
     * its lowercased value.
     */
    public static final String TYPE_ATTR_LC_VAL = toLowerCase(toXPathAttribute("type"));

    public static String getXPathAttribute(AttributeCondition attributeCondition) {
		String attributeName = attributeCondition.getLocalName();
		return toXPathAttribute(attributeName);
	}

	public static String toXPathAttribute(String attributeName) {
		String escapedAttributeName = SelectorUtils.intoEscapedXPathString(attributeName).toLowerCase();
        return "@*[" + toLowerCase("name()") + " = " + escapedAttributeName + "]";
	}

    public static String toLowerCase(String s) {
        return "translate(" + s + ", 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')";
    }

}