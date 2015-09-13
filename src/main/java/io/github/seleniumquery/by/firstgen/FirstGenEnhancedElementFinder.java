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

package io.github.seleniumquery.by.firstgen;

import io.github.seleniumquery.by.EnhancedElementFinder;
import io.github.seleniumquery.by.firstgen.xpath.TagComponentList;
import io.github.seleniumquery.by.firstgen.xpath.XPathComponentCompilerService;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * @author acdcjunior
 * @since 1.0.0
 */
public class FirstGenEnhancedElementFinder implements EnhancedElementFinder {

    /**
     * Compiles the selector for the given context (the context will determinate what selectors are natively
     * supported and what selectors should be handled by SeleniumQuery programatically) and matches elements based on it.
     */
    @Override
    public List<WebElement> findElements(SearchContext context, String selector) {
        TagComponentList xPathLocator = XPathComponentCompilerService.compileSelectorList(selector);
        return xPathLocator.findWebElements(context);
    }

}