/*******************************************************************************
 * Copyright 2016 Intuit
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.intuit.wasabi.exceptions;

import static com.intuit.wasabi.experimentobjects.exceptions.ErrorCode.EVENT_FAILED;

public class EventException extends WasabiServerException {

    private static final long serialVersionUID = 4145063198511677316L;

    public EventException(String message) {
        this(message, null);
    }

    public EventException(String message, Throwable rootCause) {
        super(EVENT_FAILED, message, rootCause);
    }

}
