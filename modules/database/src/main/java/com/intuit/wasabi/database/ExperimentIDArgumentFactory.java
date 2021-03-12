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
package com.intuit.wasabi.database;

import com.intuit.wasabi.experimentobjects.Experiment;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.Argument;
import org.skife.jdbi.v2.tweak.ArgumentFactory;

public class ExperimentIDArgumentFactory implements ArgumentFactory<Experiment.ID> {

    @Override
    public boolean accepts(Class<?> expectedType, Object value, StatementContext context) {
        return value instanceof Experiment.ID;
    }

    @Override
    public Argument build(Class<?> expectedType, Experiment.ID value, StatementContext context) {
        return new UUIDArgument(value.getRawID());
    }
}
