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

package io.github.seleniumquery;

/**
 * Generic runtime exception thrown by SeleniumQuery.
 */
public class SeleniumQueryException extends RuntimeException {

    public SeleniumQueryException(String message) {
        super(banner(message));
    }

    public SeleniumQueryException(String message, Throwable cause) {
        super(banner(message), cause);
    }

    static String banner(String message) {
        return "\n" +
            "################################################################################\n" +
            message + "\n" +
            "################################################################################";
    }

}
