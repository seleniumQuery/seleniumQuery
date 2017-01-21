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

package io.github.seleniumquery.by.secondgen.parser;

import io.github.seleniumquery.by.common.preparser.CssParsedSelector;
import io.github.seleniumquery.by.common.preparser.CssParsedSelectorList;
import io.github.seleniumquery.by.common.preparser.CssSelectorParser;
import io.github.seleniumquery.by.secondgen.csstree.CssSelectorList;
import io.github.seleniumquery.by.secondgen.csstree.selector.CssSelector;
import io.github.seleniumquery.by.secondgen.parser.translator.selector.SQCssSelectorTranslator;

import java.util.ArrayList;
import java.util.List;

public class SQParseTreeBuilder {

	private static SQCssSelectorTranslator sqCssSelectorTranslator = new SQCssSelectorTranslator();

	private SQParseTreeBuilder() {}

	public static CssSelectorList parse(String selector) {
		CssParsedSelectorList parsedSelectorList = CssSelectorParser.parseSelector(selector);
        List<CssSelector> cssSelectors = translate(parsedSelectorList);
		return new CssSelectorList(cssSelectors);
	}

    private static List<CssSelector> translate(CssParsedSelectorList parsedSelectorList) {
        List<CssSelector> cssSelectors = new ArrayList<>(parsedSelectorList.size());
        for (CssParsedSelector cssParsedSelector : parsedSelectorList) {
            cssSelectors.add(translate(cssParsedSelector));
        }
        return cssSelectors;
    }

    private static CssSelector translate(CssParsedSelector cssParsedSelector) {
        return sqCssSelectorTranslator.translate(cssParsedSelector.getArgumentMap(), cssParsedSelector.getSelector());
    }

}
