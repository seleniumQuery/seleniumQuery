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

package io.github.seleniumquery.by.css.pseudoclasses;

import io.github.seleniumquery.by.preparser.ArgumentMap;
import org.w3c.css.sac.Selector;

public class PseudoClassSelector {
	
	private ArgumentMap stringMap;
	private Selector selectorThisConditionShouldApply;
	private String pseudoClassValue;

	public PseudoClassSelector(ArgumentMap stringMap, Selector selectorThisConditionShouldApply, String pseudoClassValue) {
		this.stringMap = stringMap;
		this.selectorThisConditionShouldApply = selectorThisConditionShouldApply;
		this.pseudoClassValue = pseudoClassValue;
	}

	public String getPseudoClassContent() {
		String index = pseudoClassValue.substring(pseudoClassValue.indexOf('(')+1, pseudoClassValue.length()-1);
		return this.stringMap.get(index);
	}

	/**
	 * Represents the selector this pseudo class condition should apply to.
	 * 
	 * In other words, the selector up to the point of this pseudo class ---> #i.mean.this.selector:before-this-pseudo
	 */
	public Selector getSelector() {
		return selectorThisConditionShouldApply;
	}

	public ArgumentMap getStringMap() {
		return stringMap;
	}

	public String getOriginalPseudoClassSelector() {
		String pseudoClassBracesContent = getPseudoClassContent();
		
		String rawUsupportedSelector = ":"+getPseudoClass();
		if (pseudoClassBracesContent != null) {
			rawUsupportedSelector = ":"+getPseudoClass()+"("+pseudoClassBracesContent+")";
		}
		return rawUsupportedSelector;
	}
	
	private String getPseudoClass() {
		int openingBracket = pseudoClassValue.indexOf('(');
		if (openingBracket == -1) {
			return pseudoClassValue;
		}
		return pseudoClassValue.substring(0, openingBracket);
	}

}