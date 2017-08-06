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

package endtoend.infra;

import static io.github.seleniumquery.SeleniumQuery.$;
import static org.junit.Assert.assertEquals;
import static testinfrastructure.EndToEndTestUtils.classNameToTestFileUrl;

public class AgentHtml {

    private static final String AGENT_TEST_URL = classNameToTestFileUrl(AgentHtml.class);

    public static void assertAgentString(String agentString) {
        $.url(AgentHtml.AGENT_TEST_URL);
        assertEquals(agentString, $("#agent").text());
    }

}
