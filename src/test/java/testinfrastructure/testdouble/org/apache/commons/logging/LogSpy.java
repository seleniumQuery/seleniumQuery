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

package testinfrastructure.testdouble.org.apache.commons.logging;

import org.apache.commons.logging.Log;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class LogSpy implements Log {

    private Throwable infoLoggedThrowableClass;

    @Override public void debug(Object o) { }
    @Override public void debug(Object o, Throwable throwable) { }
    @Override public void error(Object o) { }
    @Override public void error(Object o, Throwable throwable) { }
    @Override public void fatal(Object o) { }
    @Override public void fatal(Object o, Throwable throwable) { }
    @Override public void info(Object o) { }

    @Override public void info(Object o, Throwable throwable) {
        this.infoLoggedThrowableClass = throwable;
    }

    @Override public boolean isDebugEnabled() { return false; }
    @Override public boolean isErrorEnabled() { return false; }
    @Override public boolean isFatalEnabled() { return false; }
    @Override public boolean isInfoEnabled() { return false; }
    @Override public boolean isTraceEnabled() { return false; }
    @Override public boolean isWarnEnabled() { return false; }
    @Override public void trace(Object o) { }
    @Override public void trace(Object o, Throwable throwable) { }
    @Override public void warn(Object o) { }
    @Override public void warn(Object o, Throwable throwable) { }

    public void assertInfoWithExceptionWasLogged(Class<? extends Throwable> throwableClass) {
        assertThat(this.infoLoggedThrowableClass, instanceOf(throwableClass));
    }

}
