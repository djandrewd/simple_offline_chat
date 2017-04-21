package ua.goit.offline5.chat.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.goit.offline5.chat.dao.entity.Message;

import java.util.List;

/**
 * MessagesDao
 *
 * Created by andreymi on 4/21/2017.
 */
@Component
public class MessagesDao {

    private SessionFactory sessionFactory;

    @Autowired
    public MessagesDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Message message) {
        Session session = sessionFactory.getCurrentSession();
        session.save(message);
    }

    @SuppressWarnings("unchecked")
    public List<Message> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return (List<Message>)
                session.createQuery("from Message").list();
    }

}
