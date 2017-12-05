/*
 * Copyright (c) 2016 seleniumQuery authors
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

package io.github.seleniumquery;

/**
 * An interface allows to specify the next step after evaluating for some condition: to re-evaluate more
 * (<code>.and()</code>) or to do something else (<code>.then()</code>).
 *
 * @author acdcjunior
 * @since 0.18.0
 */
@SuppressWarnings("deprecation")
public interface SeleniumQueryFluentAndOrThen extends SeleniumQueryWaitAndOrThen {

    /**
     * Allows the chaining of additional evaluation conditions.
     *
     * @return An object where it is possible to set more evaluation conditions.
     * @since 0.9.0
     */
    SeleniumQueryFluentFunction and();

    /**
     * Allows the execution of a regular function (such as <code>.click()</code>) on the elements matched after
     * the evaluation condition is met.
     *
     * @return The {@link SeleniumQueryObject} after the evaluation conditions were met.
     */
    SeleniumQueryObject then();

}
