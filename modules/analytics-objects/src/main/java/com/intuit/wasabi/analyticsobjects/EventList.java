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
package com.intuit.wasabi.analyticsobjects;

import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

/**
 * A collection of {@link Event} objects
 */
public class EventList {

    @ApiModelProperty(required = true)
    private List<Event> events = new ArrayList<>();

    public EventList() {
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> value) {
        events = value;
    }

    @Override
    public String toString() {
        if (nonNull(events)) {
            return events.toString();
        }
        return null;
    }
}
