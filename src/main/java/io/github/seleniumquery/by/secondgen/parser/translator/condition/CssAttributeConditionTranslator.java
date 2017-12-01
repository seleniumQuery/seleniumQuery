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

package io.github.seleniumquery.by.secondgen.parser.translator.condition;

import org.unbescape.java.JavaEscape;
import org.w3c.css.sac.AttributeCondition;

import io.github.seleniumquery.by.secondgen.parser.ast.condition.attribute.AstCssClassAttributeCondition;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.attribute.AstCssContainsPrefixAttributeCondition;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.attribute.AstCssContainsSubstringAttributeCondition;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.attribute.AstCssContainsWordAttributeCondition;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.attribute.AstCssEndsWithAttributeCondition;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.attribute.AstCssEqualsOrHasAttributeCondition;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.attribute.AstCssIdAttributeCondition;
import io.github.seleniumquery.by.secondgen.parser.ast.condition.attribute.AstCssStartsWithAttributeCondition;

class CssAttributeConditionTranslator {

    /**
     * .class
     *
     * @author acdcjunior
     * @since 0.10.0
     */
    AstCssClassAttributeCondition translateClasAttribute(AttributeCondition attributeCondition) {
        String wantedClassName = attributeCondition.getValue();
        String backslashEscapedClassName = wantedClassName.replaceAll("\\\\\"", "\"");
        return new AstCssClassAttributeCondition(backslashEscapedClassName);
    }

    /**
     * [languages|="pt"]
     *
     * @author acdcjunior
     * @since 0.10.0
     */
    AstCssContainsPrefixAttributeCondition translateAttributeContains(AttributeCondition attributeCondition) {
		String attributeName = attributeCondition.getLocalName();
		String wantedValue = attributeCondition.getValue();
		return new AstCssContainsPrefixAttributeCondition(attributeName, wantedValue);
	}

    /**
     * [attribute*=stringToContain]
     *
     * @author acdcjunior
     * @since 0.10.0
     */
    AstCssContainsSubstringAttributeCondition translateContainsSubstring(AttributeCondition attributeCondition) {
		String attributeName = attributeCondition.getLocalName();
		String wantedValue = attributeCondition.getValue();
		return new AstCssContainsSubstringAttributeCondition(attributeName, wantedValue);
	}

    /**
     * [values~="10"]
     *
     * @author acdcjunior
     * @since 0.10.0
     */
    AstCssContainsWordAttributeCondition translateContainsWord(AttributeCondition attributeCondition) {
        String attributeName = attributeCondition.getLocalName();
        String wantedValue = attributeCondition.getValue();
        return new AstCssContainsWordAttributeCondition(attributeName, wantedValue);
    }

    /**
     * [attribute$=stringToEnd]
     *
     * CASE SENSITIVE! http://api.jquery.com/attribute-ends-with-selector/
     *
     * @author acdcjunior
     * @since 0.9.0
     */
    AstCssEndsWithAttributeCondition translateEndsWithAtt(AttributeCondition attributeCondition) {
		String attributeName = attributeCondition.getLocalName();
		String wantedValue = attributeCondition.getValue();
		return new AstCssEndsWithAttributeCondition(attributeName, wantedValue);
	}

    /**
     * [simple]
     * [restart="never"]
     *
     * Case INsensitive!
     *
     * @author acdcjunior
     * @since 0.10.0
     */
    AstCssEqualsOrHasAttributeCondition translateEqualsOrHasAttr(AttributeCondition attributeCondition) {
		String attributeName = attributeCondition.getLocalName();
		// [attribute=wantedValue]
		if (attributeCondition.getSpecified()) {
			String wantedValue = attributeCondition.getValue();
			return new AstCssEqualsOrHasAttributeCondition(attributeName, wantedValue);
		}
		// [attribute]
		return new AstCssEqualsOrHasAttributeCondition(attributeName);
	}

    /**
     * #id
     *
     * CASE SENSITIVE!
     *
     * @author acdcjunior
     * @since 0.10.0
     */
    AstCssIdAttributeCondition translateIdAttr(AttributeCondition attributeCondition) {
		String wantedId = attributeCondition.getValue();
		String escapedId = JavaEscape.unescapeJava(wantedId);
		return new AstCssIdAttributeCondition(escapedId);
	}

    /**
     * [attribute^=stringToStart]
     *
     * CASE INsensitive!
     *
     * @author acdcjunior
     * @since 0.10.0
     */
    AstCssStartsWithAttributeCondition translateStartsWithAttr(AttributeCondition attributeCondition) {
		String attributeName = attributeCondition.getLocalName();
		String wantedValue = attributeCondition.getValue();
		return new AstCssStartsWithAttributeCondition(attributeName, wantedValue);
	}

}
