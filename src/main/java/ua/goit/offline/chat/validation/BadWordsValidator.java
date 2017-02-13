package ua.goit.offline.chat.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ua.goit.offline.chat.dao.BadWordsDao;

/**
 * Created by Dj Andrew D on 11.02.2017.
 */
@Component
public class BadWordsValidator implements Validator {

    private final BadWordsDao badWordsDao;

    @Autowired
    public BadWordsValidator(BadWordsDao badWordsDao) {
        this.badWordsDao = badWordsDao;
    }

    @Transactional(readOnly = true)
    @Override
    public boolean isValid(String validate) {
        return !StringUtils.isEmpty(validate) && badWordsDao.get(validate) == null;
    }
}
