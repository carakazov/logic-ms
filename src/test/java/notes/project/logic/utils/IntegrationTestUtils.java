package notes.project.logic.utils;

import lombok.experimental.UtilityClass;
import notes.project.logic.oauth.dto.InternalServerTokenResponseDto;
import notes.project.logic.oauth.dto.KeycloakServerTokenResponseDto;

import static notes.project.logic.utils.TestDataConstants.*;

@UtilityClass
public class IntegrationTestUtils {
    public static KeycloakServerTokenResponseDto keycloakServerTokenResponseDto() {
        return (KeycloakServerTokenResponseDto) new KeycloakServerTokenResponseDto()
            .setRefreshTokenExpiresIn(REFRESH_TOKEN_EXPIRES_IN)
            .setRefreshToken(REFRESH_TOKEN)
            .setNotBeforePolicy(NOT_BEFORE_POLICY)
            .setSessionState(SESSION_STATE)
            .setAccessToken(ACCESS_TOKEN)
            .setTokenType(TOKEN_TYPE)
            .setExpiresIn(EXPIRES_IN)
            .setScope(SCOPE);
    }

    public static InternalServerTokenResponseDto internalServerTokenResponseDto() {
        return (InternalServerTokenResponseDto) new InternalServerTokenResponseDto()
            .setJti(JTI)
            .setAccessToken(ACCESS_TOKEN)
            .setTokenType(TOKEN_TYPE)
            .setExpiresIn(EXPIRES_IN)
            .setScope(SCOPE);
    }
}
