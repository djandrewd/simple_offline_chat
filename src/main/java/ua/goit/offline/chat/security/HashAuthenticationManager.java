package ua.goit.offline.chat.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.util.StringUtils;
import ua.goit.offline.chat.dao.UserService;
import ua.goit.offline.chat.entity.Role;
import ua.goit.offline.chat.entity.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andreymi on 2/8/2017.
 */
public class HashAuthenticationManager implements AuthenticationProvider {

    private static final String SALT = "kssakmdsfbhsbdh4323423dhs";
    private static final String ROLE_PREFIX = "ROLE_";

    private UserService userService;

    public HashAuthenticationManager(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        User user;
        try {
            user = userService.getUser(username);
        } catch (Exception e) {
            throw new UsernameNotFoundException("User with name: " + username + " not found!");
        }
        if (user == null || StringUtils.isEmpty(user.getPassword())) {
            throw new UsernameNotFoundException("User with name: " + username + " not found!");
        }
        if (StringUtils.isEmpty(password)) {
            throw new BadCredentialsException("Password is empty!");
        }
        String hashed = hashPassword(password);
        if (!user.getPassword().equals(hashed)) {
            throw new BadCredentialsException("Username or password is not correct!");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.getName()));
        }
        return new UsernamePasswordAuthenticationToken(user, "******", authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

    private String hashPassword(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] result = messageDigest.digest((SALT + password + SALT).getBytes());
            return new String(Hex.encode(result));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
