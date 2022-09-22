package notes.project.logic.config;

import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import notes.project.logic.oauth.TokenSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "application")
@Component
@Accessors(chain = true)
public class ApplicationProperties {

    private Map<String, String> errorMessages;
    private InternalServerProfile internalServerProfile;
    private KeycloakServerProfile keycloakServerProfile;

    public String getMessage(String messageCode) {
        return errorMessages.get(messageCode);
    }

    @Data
    @Accessors(chain = true)
    public static class KeycloakProfile {
        protected String url;
        protected String grantType;
        protected String clientId;
    }

    @Data
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = true)
    @ToString(callSuper = true)
    public static class KeycloakServerProfile extends KeycloakProfile {
        private String username;
        private String password;
    }

    @Data
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = true)
    @ToString(callSuper = true)
    public static class InternalServerProfile extends KeycloakProfile {
        private String clientId;
        private String clientSecret;
    }
}
