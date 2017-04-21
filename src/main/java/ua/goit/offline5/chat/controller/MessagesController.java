package ua.goit.offline5.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.offline5.chat.dao.MessagesDao;

/**
 * Controller for messages in the system
 *
 * Created by andreymi on 4/21/2017.
 */
@Controller
@RequestMapping("/messages")
public class MessagesController {

    private MessagesDao messagesDao;

    @GetMapping
    @Transactional(readOnly = true)
    public ModelAndView getMessages() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("messages");
        modelAndView.addObject("msgs", messagesDao.getAll());
        return modelAndView;
    }

    @PostMapping("/create")
    public void saveMessage(String text) {


        /// TODO
    }
}
