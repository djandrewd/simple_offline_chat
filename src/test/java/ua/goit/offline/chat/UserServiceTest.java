package ua.goit.offline.chat;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;

/**
 * Created by andreymi on 2/13/2017.
 */
public class UserServiceTest {

    private UserDetailsService service;

    @Before
    public void initService() {
        service = Mockito.mock(UserDetailsService.class);
    }

    @Test
    public void testCorrect() {
        Mockito.when(service.loadUserByUsername("test"))
                .thenReturn(User.withUsername("test")
                                .password("test")
                                .authorities(Collections.emptyList())
                                .build());
        Assert.assertEquals("test", getPassword("test"));
        Mockito.verify(service, Mockito.times(1))
                .loadUserByUsername("test");
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testError() {
        Mockito.when(service.loadUserByUsername(Mockito.anyString()))
                .thenReturn(null);
        getPassword("test");
    }
    private String getPassword(String username) {
        UserDetails details = service.loadUserByUsername(username);
        if (details == null) {
            throw new UsernameNotFoundException(username);
        }
        return details.getPassword();
    }
}
