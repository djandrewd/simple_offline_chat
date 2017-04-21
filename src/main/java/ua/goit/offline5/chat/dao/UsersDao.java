package ua.goit.offline5.chat.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.goit.offline5.chat.dao.entity.User;

/**
 * Users DAO.
 * <p>
 * Created by andreymi on 4/21/2017.
 */
@Component
public class UsersDao {
    private SessionFactory sessionFactory;

    @Autowired
    public UsersDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public User get(String login) {
        Session session = sessionFactory.getCurrentSession();
        return (User) session
                .createQuery("from User where login=:login")
                .setParameter("login", login)
                .uniqueResult();
    }

    public void save(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(user);
    }
}
