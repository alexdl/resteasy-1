package com.staleylabs.resteasy.service.impl;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * StaleyLabs
 *
 * @author Sean M. Staley
 * @version 1.0 (7/30/13)
 */

public class ContactServiceImplTest {

    private ContactServiceImpl contactService;

    @Before
    public void setUp() throws Exception {
         contactService = new ContactServiceImpl();
    }

    @Test
    public void testTrimPhoneNumber_special_characters() throws Exception {
        String incomingPhone = "+1 (304).386-4522";

        long expected = 13043864522l;
        long actual = contactService.trimPhoneNumber(incomingPhone);

        assertEquals(expected, actual, 0);

    }

    @Test
    public void testTrimPhoneNumber_just_spaces() throws Exception {
        String incomingPhone = "1 304 386 4522";

        long expected = 13043864522l;
        long actual = contactService.trimPhoneNumber(incomingPhone);

        assertEquals(expected, actual, 0);

    }
}
