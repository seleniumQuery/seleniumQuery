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

package endtoend.selectors.pseudoclasses.form;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import testinfrastructure.SecondGenSelectorSystemDetector;
import testinfrastructure.junitrule.SetUpAndTearDownDriver;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ImagePseudoClassTest {

	@ClassRule @Rule public static SetUpAndTearDownDriver setUpAndTearDownDriverRule = new SetUpAndTearDownDriver();

	@Test
	public void imagePseudoClass() {
		assertThat($("[type='image']").size(), is(4));
		assertThat($(":image").size(), is(1));
		assertThat($("*:image").size(), is(1));
		assertThat($("input:image").size(), is(1));
	}

    @Test
    public void image_is() {
		assertThat($("#i1").is(":image"), is(true));
		assertThat($("#i1").is("*:image"), is(true));
		assertThat($("#i1").is("input:image"), is(true));

		assertThat($("#i2").is(":image"), is(false));
		assertThat($("#i3").is(":image"), is(false));
		assertThat($("#i4").is(":image"), is(false));
	}

    @Test
    public void imagePseudoClass__invalid() {
        SecondGenSelectorSystemDetector.assertPseudoOnDivAndSpanIsEmptyOn1stGenAndThrowsExceptionOn2ndGen(":image");
    }

}
