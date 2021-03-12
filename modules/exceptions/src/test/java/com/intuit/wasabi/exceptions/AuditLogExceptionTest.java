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

import com.intuit.wasabi.experimentobjects.exceptions.ErrorCode;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for {@link AuditLogException}
 */
public class AuditLogExceptionTest {

    @Test
    public void testAuditLogException() throws Exception {
        try {
            throw new AuditLogException("AuditLogException.");
        } catch (AuditLogException e) {
            Assert.assertTrue(e.getMessage().contains("AuditLogException."));
            Assert.assertEquals(ErrorCode.AUDIT_LOG_ERROR, e.getErrorCode());
        }
    }

    @Test
    public void testAuditLogException2() throws Exception {
        try {
            throw new AuditLogException(ErrorCode.__TEST_ERROR, "Error!");
        } catch (AuditLogException e) {
            Assert.assertTrue(e.getMessage().contains("Error!"));
            Assert.assertEquals(ErrorCode.__TEST_ERROR, e.getErrorCode());
        }
    }
}
