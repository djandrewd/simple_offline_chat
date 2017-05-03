package ua.goit.offline5.chat.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.offline5.chat.dao.UsersDao;

import java.util.Collections;

/**
 * Created by andreymi on 4/24/2017.
 */
@Component
public class ChatUserDetailsService implements UserDetailsService {

    private UsersDao usersDao;

    @Autowired
    public ChatUserDetailsService(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        ua.goit.offline5.chat.dao.entity.User user = usersDao.get(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return User.withUsername(username)
                .password(user.getPassword())
                .authorities(Collections.emptyList())
                .build();
    }
}
