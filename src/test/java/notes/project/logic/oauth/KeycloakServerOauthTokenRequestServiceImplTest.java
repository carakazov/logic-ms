package notes.project.logic.oauth;

import notes.project.logic.oauth.client.TokenRequestClient;
import notes.project.logic.oauth.dto.KeycloakServerTokenResponseDto;
import notes.project.logic.oauth.impl.KeycloakServerOauthTokenRequestServiceImpl;
import notes.project.logic.utils.ApplicationPropertiesUtils;
import notes.project.logic.utils.IntegrationTestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static notes.project.logic.utils.TestDataConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class KeycloakServerOauthTokenRequestServiceImplTest {
    @Mock
    private TokenRequestClient client;

    private OauthTokenRequestService<KeycloakServerTokenResponseDto> service;

    @BeforeEach
    void init() {
        service = new KeycloakServerOauthTokenRequestServiceImpl(
            ApplicationPropertiesUtils.applicationPropertiesForKeycloakServerOauthRestService(),
            client
        );
    }

    @Test
    void requestTokenSuccess() {
        KeycloakServerTokenResponseDto expected = IntegrationTestUtils.keycloakServerTokenResponseDto();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(TokenRequestParam.GRANT_TYPE.getParam(), GRANT_TYPE);
        params.add(TokenRequestParam.CLIENT_ID.getParam(), CLIENT_ID);
        params.add(TokenRequestParam.USERNAME.getParam(), USERNAME);
        params.add(TokenRequestParam.PASSWORD.getParam(), PASSWORD);

        when(client.requestToken(any(), any(), any())).thenReturn(expected);

        KeycloakServerTokenResponseDto actual = service.requestToken();

        assertEquals(expected, actual);

        verify(client).requestToken(KEYCLOAK_AUTH_URL, params, KeycloakServerTokenResponseDto.class);
    }
}
