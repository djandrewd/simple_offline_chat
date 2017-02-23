package ua.goit.offline.chat.validation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import ua.goit.offline.chat.dao.BadWordsDao;
import ua.goit.offline.chat.entity.BadWord;

/**
 * Created by andreymi on 2/20/2017.
 */
public class SimpleBadWordTest {
    private BadWordsValidator validator;
    private BadWordsDao dao;

    @Before
    public void init() {
        dao = Mockito.mock(BadWordsDao.class);
        validator = new BadWordsValidator(dao);
    }

    @Test
    public void testValid() {
        Mockito.when(dao.get("fff")).thenReturn(new BadWord());
        Assert.assertFalse(validator.isValid("fff"));
        Mockito.verify(dao, Mockito.times(1))
                .get("fff");
    }

    @Test
    public void testInValid() {
        Mockito.when(dao.get("fff")).thenReturn(new BadWord());
        Assert.assertFalse(validator.isValid(""));
        Mockito.verify(dao, Mockito.never()).get("fff");
    }


}
