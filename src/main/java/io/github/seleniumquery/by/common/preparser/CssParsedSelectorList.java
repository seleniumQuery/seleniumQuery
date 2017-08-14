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

package io.github.seleniumquery.by.common.preparser;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import org.w3c.css.sac.SelectorList;

import io.github.seleniumquery.by.common.preparser.w3cwithmap.W3cCssSelectorWithMap;

public class CssParsedSelectorList implements Iterable<W3cCssSelectorWithMap> {

	private final SelectorList selectorList;
	private final ArgumentMap argumentMap;
	private final List<W3cCssSelectorWithMap> w3cCssSelectorWithMaps;

	public CssParsedSelectorList(SelectorList selectorList, ArgumentMap argumentMap) {
		this.selectorList = selectorList;
		this.argumentMap = argumentMap;
		this.w3cCssSelectorWithMaps = createParsedSelectorList();
	}

	private List<W3cCssSelectorWithMap> createParsedSelectorList() {
        List<W3cCssSelectorWithMap> w3cCssSelectorWithMapList = new LinkedList<>();
		for (int i = 0; i < selectorList.getLength(); i++) {
			W3cCssSelectorWithMap w3cCssSelectorWithMap = new W3cCssSelectorWithMap(selectorList.item(i), this.argumentMap);
            w3cCssSelectorWithMapList.add(w3cCssSelectorWithMap);
		}
        return w3cCssSelectorWithMapList;
	}

	public SelectorList getSelectorList() {
		return this.selectorList;
	}

	public ArgumentMap getArgumentMap() {
		return this.argumentMap;
	}

    public int size() {
        return w3cCssSelectorWithMaps.size();
    }

    @Override
    public Iterator<W3cCssSelectorWithMap> iterator() {
        return w3cCssSelectorWithMaps.iterator();
    }

    public Stream<W3cCssSelectorWithMap> stream() {
        return w3cCssSelectorWithMaps.stream();
    }

    public W3cCssSelectorWithMap get(int index) {
        return w3cCssSelectorWithMaps.get(index);
    }

}
