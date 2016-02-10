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
 * An interface allows to specify the next step after waiting for some condition: to wait for more
 * (<code>.and()</code>) or to do something else (<code>.then()</code>).
 *
 * @author acdcjunior
 * @since 0.9.0
 */
public interface SeleniumQueryWaitAndOrThen {

    /**
     * Allows the chaining of additional waiting conditions.
     *
     * @return An object where it is possible to set more waiting conditions.
     * @since 0.9.0
     */
    SeleniumQueryWaitUntil and();

    /**
     * Allows the execution of a regular function (such as <code>.click()</code>) on the elements matched after
     * the waiting condition is met.
     *
     * @return The {@link SeleniumQueryObject} after the waiting conditions were met.
     */
    SeleniumQueryObject then();

}
