package ua.goit.offline.chat.dao;

import org.hibernate.SessionFactory;
import ua.goit.offline.chat.entity.BadWord;

/**
 * Created by Dj Andrew D on 11.02.2017.
 */
public class HibernateBadWordsDao implements BadWordsDao {

    private SessionFactory sessionFactory;

    public HibernateBadWordsDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public BadWord get(String word) {
        return sessionFactory.getCurrentSession().get(BadWord.class, word);
    }
}
