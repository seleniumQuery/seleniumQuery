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

package io.github.seleniumquery.by.secondgen.csstree.condition.pseudoclass;

import io.github.seleniumquery.by.common.pseudoclass.PseudoClass;
import org.openqa.selenium.InvalidSelectorException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Superclass for all functional pseudo-classes that have an integer index as argument.</p>
 * <p>E.g. {@code *:my-pseudo(123),*:my-pseudo(+77),*:my-pseudo(-35)}</p>
 *
 * @author acdcjunior
 * @since 0.10.0
 */
public abstract class SQCssFunctionalIndexArgumentPseudoClassCondition extends SQCssFunctionalPseudoClassCondition {

    private static final Pattern INDEX_REGEX = Pattern.compile("^\\s*([+-]?\\d+)\\s*$");

    public SQCssFunctionalIndexArgumentPseudoClassCondition(PseudoClass pseudoClassSelector) {
        super(pseudoClassSelector);
    }

    protected int getArgumentAsIndex() {
        String eqPseudoClassArgument = getArgument();
        Matcher m = INDEX_REGEX.matcher(eqPseudoClassArgument);
        boolean isArgumentAnInteger = m.find();
        if (!isArgumentAnInteger) {
            String reason = String.format("The :%s() pseudo-class requires an integer as argument but got: \"%s\".",
                    getPseudoClassName(), eqPseudoClassArgument);
            throw new InvalidSelectorException(reason);
        }
        String integerIndex = m.group(1);
        if (integerIndex.startsWith("+")) {
            integerIndex = integerIndex.substring(1);
        }
        return Integer.valueOf(integerIndex);
    }

    protected abstract String getPseudoClassName();

}