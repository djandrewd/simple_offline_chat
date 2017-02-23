package ua.goit.offline.chat.validation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;


public class AnotherURLValidatorTest {
    private URLLinksValidator validator;

    // 1 times @BeforeClass
    @BeforeClass
    public static void initClass() {
        System.out.println("Before call!");
    }

    // every time before each test
    @Before
    public void init() {
        System.out.println("Call before every time.");
        validator = new URLLinksValidator();
    }

    //
    @Test
    public void testCorrectMessages() {
        Assert.assertTrue(validator.isValid("hello"));
    }

    @Test
    public void testIncorrectMessages() {
        Assert.assertEquals(false, validator.isValid("http://ukr.net"));
        Assert.assertFalse(validator.isValid("http://google.com"));
    }

    @Test
    public void testWithMocks() {
        validator = Mockito.mock(URLLinksValidator.class);
        Mockito.when(validator.isValid("hello")).thenReturn(true);
        Mockito.when(validator.isValid("http://ok.com")).thenReturn(true);
        Assert.assertTrue(validator.isValid("hello"));
        Assert.assertTrue(validator.isValid("http://ok.com"));
        Mockito.verify(validator, Mockito.times(1))
                .isValid("hello");
        Mockito.verify(validator, Mockito.times(2))
                .isValid(Mockito.anyString());
    }

    //@After
    ///@AfterClass

}
