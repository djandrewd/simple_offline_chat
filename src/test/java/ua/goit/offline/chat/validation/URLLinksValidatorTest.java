package ua.goit.offline.chat.validation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Dj Andrew D on 11.02.2017.
 */
public class URLLinksValidatorTest {

    private URLLinksValidator validator;

    @Before
    public void setUp() throws Exception {
        validator = new URLLinksValidator();
    }

    @Test
    public void validWord() throws Exception {
        assertTrue(validator.isValid("hello"));
    }

    @Test
    public void notValidWord() throws Exception {
        assertFalse(validator.isValid("http://hello.com"));
        assertFalse(validator.isValid("https://hello.com"));
    }
}