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
package com.intuit.wasabi.email.impl;

import com.google.common.collect.Lists;
import com.intuit.wasabi.authenticationobjects.UserInfo;
import com.intuit.wasabi.email.EmailLinksList;
import com.intuit.wasabi.email.EmailTextProcessor;
import com.intuit.wasabi.exceptions.WasabiEmailException;
import com.intuit.wasabi.experimentobjects.Application;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * This class tests the functionality of the {@link com.intuit.wasabi.email.impl.EmailServiceImpl}
 *
 */
public class EmailServiceImplTest {

    private EmailServiceImpl emailService;

    @Before
    public void setUp() {
        emailService = new EmailServiceImpl(true, "host1", "from1", "prefix1", "username","",false, false, new EmailTextProcessorImpl(null));
    }

    @Test
    public void testValidEmailSending() {
        String[] addresses = {"foo@example.com", "bar@abc.de", "bar@abc.de", "aslkd@??."};
        String[] cleaned = emailService.removeInvalidEmails(addresses);
        assertEquals(2, cleaned.length);
        assertTrue(Lists.newArrayList(cleaned).contains("foo@example.com"));
        assertTrue(Lists.newArrayList(cleaned).contains("bar@abc.de"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidHost() {
        emailService.setHost("validHost.you.org");
        assertEquals("validHost.you.org", emailService.getHost());
        emailService.setHost("");
    }

    @Test
    public void testSimpleSetters() {
        emailService.setHost("validHost.you.org");
        assertEquals("validHost.you.org", emailService.getHost());
        emailService.setActive(false);
        assertFalse(emailService.isActive());
        emailService.setFrom("asldk@aslf.com");
        assertEquals("asldk@aslf.com", emailService.getFrom());
        emailService.setFrom("asdasdas");
        assertEquals("wasabi-service@example.com", emailService.getFrom());
        emailService.setSubjectPrefix("[PREFIX]");
        assertEquals("[PREFIX]", emailService.getSubjectPrefix());
    }

    @Test(expected = WasabiEmailException.class)
    public void testNoSendWhenInactive() {
        emailService.setActive(false);
        emailService.doSend("Test Email", "This is an email generated by the UnitTests", "me");
    }

    @Test(expected = WasabiEmailException.class)
    public void testSetEmailTextProcessor() {
        EmailTextProcessor emailTextProcessor = mock(EmailTextProcessor.class);
        EmailServiceImpl emailService = spy(new EmailServiceImpl(false, "host1", "from1", "prefix","username","", false,false, emailTextProcessor));
        Email simpleEmail = mock(Email.class);
        List<String> emailList = new ArrayList();
        emailList.add("mocked emmail");
        when(emailService.createSimpleMailService()).thenReturn(simpleEmail);

        Application.Name appName = Application.Name.valueOf("MockedName");
        UserInfo.Username username = UserInfo.Username.valueOf("mocked_user");
        EmailLinksList emailInfo = EmailLinksList.withEmailLinksList(emailList).build();
        emailService.sendEmailForUserPermission(appName, username, emailInfo);
        verify(emailTextProcessor, times(1)).getSubject(appName);
        verify(emailTextProcessor, times(1)).getMessage(appName, username, emailInfo);
        verify(emailTextProcessor, times(1)).getAddressees(appName);
    }

    @Test(expected = WasabiEmailException.class)
    public void testSend() throws EmailException {
        EmailTextProcessor emailTextProcessor = mock(EmailTextProcessor.class);
        Email simpleEmail = mock(Email.class);
        EmailServiceImpl emailService = spy(new EmailServiceImpl
                (false, "host1", "from1", "prefix","username","", false,false, emailTextProcessor));
        emailService.setActive(true);
        when(emailService.createSimpleMailService()).thenReturn(simpleEmail);
        emailService.send("mocked subject", "mocked message", "mock@example.com");
        verify(simpleEmail, times(1)).send();

        doThrow(new EmailException()).when(simpleEmail).send();
        emailService.send("mocked subject", "mocked message", "mock@example.com");
    }
}
