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

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static org.mockito.Mockito.mock;

public class LogInjector {

    private static Log createLogSpy() {
        return mock(Log.class);
    }

    public static Log injectLogSpy(Class<?> clazz) {
        Log logSpy = createLogSpy();
        setFinalStaticField(clazz, "LOGGER", logSpy);
        return logSpy;
    }

    private static void setFinalStaticField(Class<?> clazz, String fieldName, Object value) {
        try {
            setFinalStaticField(clazz.getDeclaredField(fieldName), value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void setFinalStaticField(Field field, Object newValue) {
        try {
            field.setAccessible(true);

            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

            field.set(null, newValue);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}