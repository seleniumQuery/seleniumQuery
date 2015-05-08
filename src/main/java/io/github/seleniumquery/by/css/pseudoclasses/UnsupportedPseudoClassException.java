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
import org.w3c.css.sac.SimpleSelector;

public class UnsupportedPseudoClassException extends RuntimeException {
	
	public UnsupportedPseudoClassException(String pseudoClass) {
		this(pseudoClass, "");
	}

	public UnsupportedPseudoClassException(String pseudoClass, String reason) {
		super("The pseudo-class \""+pseudoClass+"\" is not supported" + (reason.isEmpty() ? "." : ": "+reason));
	}

	public UnsupportedPseudoClassException(ArgumentMap argumentMap, SimpleSelector selectorUpToThisPoint, String pseudoClassValue) {
		this(new PseudoClassSelector(argumentMap, selectorUpToThisPoint, pseudoClassValue).getOriginalPseudoClassSelector());
	}

}