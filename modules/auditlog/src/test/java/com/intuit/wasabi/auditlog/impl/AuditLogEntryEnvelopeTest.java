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
package com.intuit.wasabi.auditlog.impl;

import com.intuit.wasabi.auditlogobjects.AuditLogEntry;
import com.intuit.wasabi.repository.AuditLogRepository;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Tests for {@link AuditLogEntryEnvelope}.
 */
public class AuditLogEntryEnvelopeTest {

    @Test
    public void testEnvelope() {
        AuditLogEntry entry = Mockito.mock(AuditLogEntry.class);
        AuditLogRepository repository = Mockito.mock(AuditLogRepository.class);

        AuditLogEntryEnvelope envelope = new AuditLogEntryEnvelope(entry, repository);
        envelope.run();
    }
}
