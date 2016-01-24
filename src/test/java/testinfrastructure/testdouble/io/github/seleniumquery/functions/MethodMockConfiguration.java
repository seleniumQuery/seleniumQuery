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

package testinfrastructure.testdouble.io.github.seleniumquery.functions;

import static org.hamcrest.collection.IsArrayContainingInOrder.arrayContaining;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class MethodMockConfiguration<T> {

    public static <T> MethodMockConfiguration<T> configureReturnValue(T returnValue) {
        return new MethodMockConfiguration<>(returnValue);
    }

    private Object[] expectedArguments;
    private T returnValue;

    public MethodMockConfiguration(T returnValue) {
        this.returnValue = returnValue;
    }

    public MethodMockConfiguration<T> forArgs(Object... expectedArguments) {
        this.expectedArguments = expectedArguments;
        return this;
    }

    public T executeMethodMock(Object... actualArguments) {
        assertThat(actualArguments, arrayContaining(this.expectedArguments));
        return this.returnValue;
    }

}
