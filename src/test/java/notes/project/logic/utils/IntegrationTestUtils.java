package notes.project.logic.utils;

import java.util.Collections;

import lombok.experimental.UtilityClass;
import notes.project.logic.dto.integration.filesystem.CreateClusterRequestDto;
import notes.project.logic.dto.integration.filesystem.CreateClusterResponseDto;
import notes.project.logic.dto.integration.userdatasystem.UserDataSystemAdditionalInfoDto;
import notes.project.logic.dto.integration.userdatasystem.UserDataSystemPersonalInfoDto;
import notes.project.logic.oauth.dto.InternalServerTokenResponseDto;
import notes.project.logic.oauth.dto.KeycloakServerTokenResponseDto;

import static notes.project.logic.utils.TestDataConstants.*;

@UtilityClass
public class IntegrationTestUtils {
    public static UserDataSystemPersonalInfoDto userDataSystemPersonalInfoDto() {
        return new UserDataSystemPersonalInfoDto()
            .setName(CLIENT_NAME)
            .setSurname(CLIENT_SURNAME)
            .setMiddleName(CLIENT_MIDDLE_NAME)
            .setBirthDate(CLIENT_BIRTH_DATE)
            .setExternalId(CLIENT_EXTERNAL_ID)
            .setAdditionalInfo(Collections.singletonList(userDataSystemAdditionalInfoDto()));
    }

    public static UserDataSystemAdditionalInfoDto userDataSystemAdditionalInfoDto() {
        return new UserDataSystemAdditionalInfoDto()
            .setType(CLIENT_ADDITIONAL_INFO_TYPE)
            .setValue(CLIENT_ADDITIONAL_INFO_VALUE);
    }

    public static CreateClusterRequestDto createClusterRequestDto() {
        return new CreateClusterRequestDto()
            .setClusterTitle(CLUSTER_TITLE);
    }

    public static CreateClusterResponseDto createClusterResponseDto() {
        return new CreateClusterResponseDto()
            .setCreateDate(CLUSTER_CREATE_DATE)
            .setExternalId(CLUSTER_EXTERNAL_ID)
            .setTitle(CLUSTER_TITLE);
    }

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
