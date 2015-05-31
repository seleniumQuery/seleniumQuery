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

package testinfrastructure.testutils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import static org.mockito.Mockito.verify;

public class LogInjectorTest {

    private static final Log LOGGER = LogFactory.getLog(LogInjectorTest.class);

    @Test
    public void injectLogSpy__managesToInjectLog() {
        // when
        Log logSpy = LogInjector.injectLogSpy(LogInjectorTest.class);
        // then
        LOGGER.info("SomeMessage");
        verify(logSpy).info("SomeMessage");
    }

}