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

package io.github.seleniumquery.by2.parser;

import io.github.seleniumquery.by.csstree.SQCssSelectorList;
import io.github.seleniumquery.by.csstree.selector.SQCssSelector;
import io.github.seleniumquery.by2.parser.translator.selector.SQCssSelectorTranslator;
import io.github.seleniumquery.by.preparser.ArgumentMap;
import io.github.seleniumquery.by.preparser.CSSParsedSelectorList;
import io.github.seleniumquery.by.preparser.CSSSelectorParser;
import org.w3c.css.sac.Selector;
import org.w3c.css.sac.SelectorList;

import java.util.ArrayList;
import java.util.List;

public class SQParseTreeBuilder {

	private static SQCssSelectorTranslator sqCssSelectorTranslator = new SQCssSelectorTranslator();
	
	public static SQCssSelectorList parse(String selector) {
		CSSParsedSelectorList cssParsedSelectorList = CSSSelectorParser.parseSelector(selector);
		SelectorList selectorList = cssParsedSelectorList.getSelectorList();

    	List<SQCssSelector> css = new ArrayList<SQCssSelector>(selectorList.getLength());
    	for (int i = 0; i < selectorList.getLength(); i++) {
			SQCssSelector cs = translate(cssParsedSelectorList.getArgumentMap(), selectorList.item(i));
    		css.add(cs);
    	}
    	
    	return new SQCssSelectorList(css);
	}
    
	public static SQCssSelector translate(ArgumentMap argumentMap, Selector selector) {
		return sqCssSelectorTranslator.translate(argumentMap, selector);
	}

}