package notes.project.logic.utils.helper;

import java.util.UUID;

import notes.project.logic.utils.AuthHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class AuthHelperImpl implements AuthHelper {
    private static final String CLIENT_EXTERNAL_ID_CLAIM = "externalId";

    @Override
    public UUID getAuthorizedClientId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getCredentials();
        return UUID.fromString(jwt.getClaimAsString(CLIENT_EXTERNAL_ID_CLAIM));
    }
}
