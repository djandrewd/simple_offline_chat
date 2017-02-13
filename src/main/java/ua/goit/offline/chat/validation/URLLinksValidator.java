package ua.goit.offline.chat.validation;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

/**
 * Created by Dj Andrew D on 11.02.2017.
 */
@Component
public class URLLinksValidator implements Validator {

    private static final String HTTP_PATTERN = "https?://[a-zA-Z0-9/.-/w]*";

    @Override
    public boolean isValid(String validate) {
        return !StringUtils.isEmpty(validate) && !validate.matches(HTTP_PATTERN);
    }
}
