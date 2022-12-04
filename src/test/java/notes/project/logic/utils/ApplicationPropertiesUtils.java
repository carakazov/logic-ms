package notes.project.logic.utils;

import java.util.Map;

import lombok.experimental.UtilityClass;
import notes.project.logic.config.ApplicationProperties;
import notes.project.logic.dto.integration.rabbit.EventCode;

import static notes.project.logic.utils.TestDataConstants.*;

@UtilityClass
public class ApplicationPropertiesUtils {
    public static ApplicationProperties applicationProperties() {
        return new ApplicationProperties();
    }

    public static ApplicationProperties getApplicationPropertiesForMailTemplateHelperTest() {
        return new ApplicationProperties()
            .setMessageTemplates(
                Map.of(
                    EventCode.CLUSTER_WILL_BE_DELETED_SOON.getCode(),
                    MESSAGE_TEMPLATE
                )
            );
    }

    public static ApplicationProperties getApplicationPropertiesForClientService() {
        return new ApplicationProperties()
            .setSystemName(SYSTEM_NAME);
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
