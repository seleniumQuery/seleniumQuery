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

package io.github.seleniumquery.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Should hold a bunch of utility functions.
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public class ListUtils {

    public static <T> List<T> toImmutableRandomAccessList(List<T> els) {
        return Collections.unmodifiableList(new ArrayList<>(els));
    }

}
