package ua.goit.offline5.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.offline5.chat.dao.MessagesDao;
import ua.goit.offline5.chat.dao.UsersDao;
import ua.goit.offline5.chat.dao.entity.Message;
import ua.goit.offline5.chat.dao.entity.User;

/**
 * Controller for messages in the system
 *
 * Created by andreymi on 4/21/2017.
 */
@Controller
@RequestMapping("/messages")
public class MessagesController {

    private final MessagesDao messagesDao;
    private final UsersDao usersDao;

    @Autowired
    public MessagesController(MessagesDao messagesDao, UsersDao usersDao) {
        this.messagesDao = messagesDao;
        this.usersDao = usersDao;
    }

    @GetMapping
    @Transactional(readOnly = true)
    public ModelAndView getMessages() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("messages");
        modelAndView.addObject("msgs", messagesDao.getAll());
        return modelAndView;
    }

    @PostMapping("/create")
    @Transactional
    public void saveMessage(String text) {
        User user = usersDao.get("test");
        Message message = new Message();
        message.setText(text);
        message.setUser(user);
        messagesDao.save(message);
    }
}
