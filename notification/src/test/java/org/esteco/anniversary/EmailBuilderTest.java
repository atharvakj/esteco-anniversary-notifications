package org.esteco.anniversary;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EmailBuilderTest {

    @Test
    public void testBuildBirthDayEmail() {
        Email email = Email.builder()
                    .from("jaiswal@esteco.com")
                    .to("honule@esteco.com")
                    .cc("india@esteco.com")
                    .body("Happy Birthday")
                    .subject("It's Vivek's Birthday")
                    .build();

        assertEquals("jaiswal@esteco.com",email.getFrom());
    }
}
