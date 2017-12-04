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

package io.github.seleniumquery.wait;

public enum FluentBehaviorModifier {

    REGULAR_BEHAVIOR("") {
        @Override
        public FluentBehaviorModifier negate() {
            return NEGATED_BEHAVIOR;
        }

        @Override
        public boolean isExpectedBehavior(boolean satisfiesConstraints) {
            return satisfiesConstraints;
        }
    },
    NEGATED_BEHAVIOR(".not()") {
        @Override
        public FluentBehaviorModifier negate() {
            return REGULAR_BEHAVIOR;
        }

        @Override
        public boolean isExpectedBehavior(boolean satisfiesConstraints) {
            return !satisfiesConstraints;
        }
    };

    private final String toString;

    FluentBehaviorModifier(String toString) {
        this.toString = toString;
    }

    @Override
    public String toString() {
        return this.toString;
    }

    public abstract FluentBehaviorModifier negate();

    public abstract boolean isExpectedBehavior(boolean satisfiesConstraints);

    public boolean isNotExpectedBehavior(boolean satisfiesConstraints) {
        return !this.isExpectedBehavior(satisfiesConstraints);
    }

}
