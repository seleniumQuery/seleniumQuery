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

package io.github.seleniumquery.by.secondgen.csstree;

import io.github.seleniumquery.by.secondgen.csstree.selector.CssSelector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * A list of CSS Selectors (objects that represent parsed CSS selectors).
 *
 * Each {@link CssSelector} ia a root of a CSS selector object tree.
 */
public class CssSelectorList  {

    private List<CssSelector> cssSelectors;

    public CssSelectorList(List<CssSelector> cssSelectors) {
        this.cssSelectors = Collections.unmodifiableList(new ArrayList<>(cssSelectors));
    }

    public CssSelector firstSelector() {
        return stream().findFirst().get();
    }

    public Stream<CssSelector> stream() {
        return cssSelectors.stream();
    }

}
