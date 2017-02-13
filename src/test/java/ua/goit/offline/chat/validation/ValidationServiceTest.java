package ua.goit.offline.chat.validation;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.goit.offline.chat.dao.BadWordsDao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Dj Andrew D on 11.02.2017.
 */
public class ValidationServiceTest {
    @Configuration
    public static class TestDaoConfig {
        @Bean
        public BadWordsDao badWordsDao() {
            return mock(BadWordsDao.class);
        }
    }

    private ValidationService service;
    private BadWordsDao badWordsDao;

    @Before
    public void setUp() throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(TestDaoConfig.class);
        context.scan("ua.goit.offline.chat.validation");
        context.refresh();
        service = context.getBean(ValidationService.class);
        badWordsDao = context.getBean(BadWordsDao.class);
    }

    @Test
    public void testWordCorrect() {
        when(badWordsDao.get("test")).thenReturn(null);
        assertTrue(service.isValid("test"));
        verify(badWordsDao, times(1)).get("test");
    }

    @Test
    public void testWordInCorrect() {
        String message = "https://hello.com";
        when(badWordsDao.get(message)).thenReturn(null);
        assertFalse(service.isValid(message));
    }

}