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

package io.github.seleniumquery.by.secondgen.parser;

import java.util.List;
import java.util.stream.Collectors;

import io.github.seleniumquery.by.common.preparser.CssSelectorParser;
import io.github.seleniumquery.by.common.preparser.w3cwithmap.W3cCssSelectorListWithMap;
import io.github.seleniumquery.by.secondgen.csstree.CssSelectorList;
import io.github.seleniumquery.by.secondgen.csstree.selector.CssSelector;
import io.github.seleniumquery.by.secondgen.parser.translator.CssSelectorTranslator;

public class ParseTreeBuilder {

	private static CssSelectorTranslator cssSelectorTranslator = new CssSelectorTranslator();

	private ParseTreeBuilder() {}

	public static CssSelectorList parse(String selector) {
		W3cCssSelectorListWithMap parsedSelectorList = CssSelectorParser.parseSelector(selector);
        List<CssSelector> cssSelectors = translate(parsedSelectorList);
		return new CssSelectorList(cssSelectors);
	}

    private static List<CssSelector> translate(W3cCssSelectorListWithMap parsedSelectorList) {
	    return parsedSelectorList.stream().map(cssParsedSelector -> cssSelectorTranslator.translate(cssParsedSelector)).collect(Collectors.toList());
    }

}
