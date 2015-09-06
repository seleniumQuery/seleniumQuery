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

package io.github.seleniumquery.by2.parser.translator.selector;

import io.github.seleniumquery.by2.csstree.selector.SQCssTagNameSelector;
import org.w3c.css.sac.ElementSelector;

/**
 * $("tagname")
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class SQCssTagNameSelectorTranslator {

	public SQCssTagNameSelector translate(ElementSelector elementSelector) {
		String tagName = elementSelector.toString();
		return new SQCssTagNameSelector(tagName);
	}

}