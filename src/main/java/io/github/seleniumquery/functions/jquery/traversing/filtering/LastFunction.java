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

package io.github.seleniumquery.functions.jquery.traversing.filtering;

import io.github.seleniumquery.SeleniumQueryObject;

/**
 * http://api.jquery.com/last/
 *
 * $("selector").last()
 *
 * @author acdcjunior
 * @author ricardo-sc
 *
 * @since 0.9.0
 */
public class LastFunction {

	private static final int LAST_ELEMENT_INDEX = -1;
	
	private LastFunction() {}

	public static SeleniumQueryObject last(SeleniumQueryObject seleniumQueryObject) {
		return EqFunction.eq(seleniumQueryObject, LAST_ELEMENT_INDEX);
	}

}