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

package io.github.seleniumquery.by.csstree.condition.pseudoclass.visibility;

import io.github.seleniumquery.by.css.pseudoclasses.HiddenPseudoClass;
import io.github.seleniumquery.by.locator.SQLocator;
import io.github.seleniumquery.by.locator.SQLocatorUtilsTest;
import org.junit.Test;

import static io.github.seleniumquery.by.csstree.condition.pseudoclass.PseudoClassTestUtils.assertPseudo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.assertThat;

public class SQCssHiddenPseudoClassTest {

    @Test
    public void translate() {
        assertPseudo(":hidden", SQCssHiddenPseudoClass.class);
    }

    @Test
    public void toSQLocator__no_browser_has_native_support() {
        // given
        SQCssHiddenPseudoClass hiddenPseudoClass = new SQCssHiddenPseudoClass();
        SQLocator previous = SQLocatorUtilsTest.tagAsterisk(SQLocatorUtilsTest.createMockDriverWithoutNativeSupportFor(":hidden"));
        // when
        SQLocator locator = hiddenPseudoClass.toSQLocator(previous);
        // then
        assertThat(locator.getCssSelector(), is(previous.getCssSelector()));
        assertThat(locator.getXPathExpression(), is(previous.getXPathExpression()));
        assertThat(locator.canPureCss(), is(false));
        assertThat(locator.canPureXPath(), is(false));
        assertThat(locator.getElementFilterList().getElementFilters(), contains(HiddenPseudoClass.HIDDEN_PSEUDO_CLASS.hiddenPseudoClassFilter));
    }

}