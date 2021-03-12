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
package com.intuit.wasabi.assignment.impl;

import com.intuit.wasabi.assignmentobjects.AssignmentEnvelopePayload;
import com.intuit.wasabi.export.DatabaseExport;
import com.intuit.wasabi.export.Envelope;

/**
 * Noop implementation for assignment database analytics export
 */
public class NoopDatabaseAssignmentEnvelope implements Envelope<AssignmentEnvelopePayload, DatabaseExport> {

    private AssignmentEnvelopePayload payload;

    public NoopDatabaseAssignmentEnvelope() {
        super();
    }

    @Override
    public void run() {
        // NOOP
    }

    @Override
    public NoopDatabaseAssignmentEnvelope withPayload(AssignmentEnvelopePayload payload) {
        this.payload = payload;
        return this;
    }

}
