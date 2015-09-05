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

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void toImmutableRandomAccessList__returned_list_should_not_get_updated_if_source_list_does() {
        List<String> sourceList = new ArrayList<String>();
        String s1 = "just some random value to prove we can add strings to this list";
        sourceList.add(s1);

        List<String> immutableSS = ListUtils.toImmutableRandomAccessList(sourceList);
        assertThat(immutableSS, contains(s1));

        String s2 = "some other value that shouldn't go into the immutable copy";
        sourceList.add(s2);
        assertThat(immutableSS, contains(s1));
    }

}