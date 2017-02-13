package ua.goit.offline.chat.validation;

import org.junit.Before;
import org.junit.Test;
import ua.goit.offline.chat.dao.BadWordsDao;
import ua.goit.offline.chat.entity.BadWord;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Dj Andrew D on 11.02.2017.
 */
public class BadWordsValidatorTest {
    private BadWordsValidator validator;
    private BadWordsDao dao;

    @Before
    public void setUp() throws Exception {
        dao = mock(BadWordsDao.class);
        validator = new BadWordsValidator(dao);
    }

    @Test
    public void validWord() {
        when(dao.get("spring")).thenReturn(null);
        assertTrue(validator.isValid("spring"));
        verify(dao, times(1)).get("spring");
    }

    @Test
    public void notValidWord() {
        when(dao.get("spring")).thenReturn(new BadWord());
        assertFalse(validator.isValid("spring"));
        verify(dao, times(1)).get("spring");
    }

    @Test
    public void empty() {
        assertFalse(validator.isValid(""));
        verify(dao, never()).get("");
    }

}