package notes.project.logic.utils;

import lombok.experimental.UtilityClass;
import notes.project.logic.config.ApplicationProperties;

import static notes.project.logic.utils.TestDataConstants.*;

@UtilityClass
public class ApplicationPropertiesUtils {
    public static ApplicationProperties applicationProperties() {
        return new ApplicationProperties();
    }

    public static ApplicationProperties applicationPropertiesForInternalServerOauthRestService() {
        return new ApplicationProperties()
            .setInternalServerProfile(
                (ApplicationProperties.InternalServerProfile) new ApplicationProperties.InternalServerProfile()
                    .setClientSecret(CLIENT_SECRET)
                    .setClientId(CLIENT_ID)
                    .setUrl(INTERNAL_AUTH_URL)
                    .setGrantType(GRANT_TYPE)
            );
    }

    public static ApplicationProperties applicationPropertiesForKeycloakServerOauthRestService() {
        return new ApplicationProperties()
            .setKeycloakServerProfile(
                (ApplicationProperties.KeycloakServerProfile) new ApplicationProperties.KeycloakServerProfile()
                    .setUsername(USERNAME)
                    .setPassword(PASSWORD)
                    .setUrl(KEYCLOAK_AUTH_URL)
                    .setGrantType(GRANT_TYPE)
                    .setClientId(CLIENT_ID)
            );
    }
}
