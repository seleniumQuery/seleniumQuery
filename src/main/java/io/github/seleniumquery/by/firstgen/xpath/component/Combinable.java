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

public interface Combinable<T extends XPathComponent> {

    /**
     * Creates a copy of the current component.
     * @return A copy of this component.
     */
    T cloneComponent();

    /**
     * This method clones the current component, clones the component sent as argument and, finally,
     * combines them, returning the combined component, that has the type of the current component.
     * @param other The component that will be cloned and combined to the clone of this current instance.
     * @return A new component, that is the result of the combination of the current instance, plus a clone of the
     * other sent as argument.
     */
    T cloneAndCombineTo(Combinable other);

}