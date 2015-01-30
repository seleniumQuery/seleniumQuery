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

package io.github.seleniumquery.by.csstree.condition.pseudoclass.form;

import io.github.seleniumquery.by.filter.ElementFilter;
import io.github.seleniumquery.by.locator.SQLocator;
import io.github.seleniumquery.by.locator.SQLocatorUtilsTest;
import org.junit.Test;

import static io.github.seleniumquery.by.csstree.condition.pseudoclass.PseudoClassTestUtils.assertPseudo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.assertThat;

public class SQCssDisabledPseudoClassTest {

    @Test
    public void translate() {
        assertPseudo(":disabled", SQCssDisabledPseudoClass.class);
    }

    @Test
    public void toSQLocator__when_driver_has_native_support() {
        // given
        SQCssDisabledPseudoClass disabledPseudoClass = new SQCssDisabledPseudoClass();
        SQLocator previous = SQLocatorUtilsTest.tagAsterisk(SQLocatorUtilsTest.createMockDriverWithNativeSupportFor(":disabled"));
        // when
        SQLocator locator = disabledPseudoClass.toSQLocator(previous);
        // then
        assertThat(locator.getCssSelector(), is(":disabled"));
        assertThat(locator.getXPathExpression(), is(".//*[(@disabled and " +
                "(self::input or self::button or self::optgroup or self::option or self::select or self::textarea)" +
            ")]"));
        assertThat(locator.canPureCss(), is(true));
        assertThat(locator.canPureXPath(), is(true));
        assertThat(locator.getElementFilterList().getElementFilters(), contains(ElementFilter.FILTER_NOTHING));
    }

    @Test
    public void toSQLocator__when_driver_does_NOT_have_native_support() {
        // given
        SQCssDisabledPseudoClass disabledPseudoClass = new SQCssDisabledPseudoClass();
        SQLocator previous = SQLocatorUtilsTest.tagAsterisk(SQLocatorUtilsTest.createMockDriverWithoutNativeSupportFor(":disabled"));
        // when
        SQLocator locator = disabledPseudoClass.toSQLocator(previous);
        // then
        assertThat(locator.getCssSelector(), is("*"));
        assertThat(locator.getXPathExpression(), is(".//*[(@disabled and " +
                "(self::input or self::button or self::optgroup or self::option or self::select or self::textarea)" +
            ")]"));
        assertThat(locator.canPureCss(), is(false));
        assertThat(locator.canPureXPath(), is(true));
        assertThat(locator.getElementFilterList().getElementFilters(), contains(ElementFilter.FILTER_NOTHING));
    }

}