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

package io.github.seleniumquery.by.xpath;

import io.github.seleniumquery.by.css.CssSelector;
import io.github.seleniumquery.by.css.CssSelectorFactory;
import io.github.seleniumquery.by.preparser.ArgumentMap;
import io.github.seleniumquery.by.preparser.CSSParsedSelectorList;
import io.github.seleniumquery.by.preparser.CSSSelectorParser;
import io.github.seleniumquery.by.xpath.component.TagComponent;
import org.w3c.css.sac.Selector;
import org.w3c.css.sac.SelectorList;

import java.util.ArrayList;
import java.util.List;

public class XPathComponentCompilerService {
	
	public static TagComponentList compileSelectorList(String selector) {
		CSSParsedSelectorList CSSParsedSelectorList = CSSSelectorParser.parseSelector(selector);
		SelectorList selectorList = CSSParsedSelectorList.getSelectorList();

    	List<TagComponent> css = new ArrayList<TagComponent>(selectorList.getLength());
    	for (int i = 0; i < selectorList.getLength(); i++) {
			TagComponent cs = compileSelector(CSSParsedSelectorList.getStringMap(), selectorList.item(i));
    		css.add(cs);
    	}
    	
    	return new TagComponentList(css);
	}
    
	public static TagComponent compileSelector(ArgumentMap stringMap, Selector selector) {
		CssSelector<Selector, TagComponent> cssSelector = CssSelectorFactory.parsedSelectorToCssSelector(selector);
		return cssSelector.toXPath(stringMap, selector);
	}

}