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

package io.github.seleniumquery.by.firstgen.xpath.component;

import io.github.seleniumquery.by.common.elementfilter.ElementFilter;
import io.github.seleniumquery.by.common.elementfilter.ElementFilterList;

import java.util.ArrayList;
import java.util.List;

public class ComponentUtils {
	
	private ComponentUtils() {}

    public static List<XPathComponent> joinComponents(List<XPathComponent> oneComponents, XPathComponent otherCopyWithModifiedType) {
        List<XPathComponent> aggregatedComponents = new ArrayList<>(oneComponents);
        aggregatedComponents.add(otherCopyWithModifiedType);
        aggregatedComponents.addAll(otherCopyWithModifiedType.combinatedComponents);
        return aggregatedComponents;
    }

    public static ElementFilterList joinFilters(ElementFilterList oneElementFilterList,
                                                @SuppressWarnings("UnusedParameters") XPathComponent otherCopyWithModifiedType) {
        // should combine otherCopyWithModifiedType.elementFilterList here as well
        return oneElementFilterList;
    }

    static ElementFilterList toElementFilterList(ElementFilter filter) {
        if (filter == ElementFilter.FILTER_NOTHING) {
            return emptyElementFilterList();
        }
        ArrayList<ElementFilter> elementFilters = new ArrayList<>();
        elementFilters.add(filter);
        return new ElementFilterList(elementFilters);
    }

    static ElementFilterList emptyElementFilterList() {
        return new ElementFilterList(new ArrayList<ElementFilter>());
    }

    public static String removeBraces(String src) {
        return src.substring(1, src.length()-1);
    }
}