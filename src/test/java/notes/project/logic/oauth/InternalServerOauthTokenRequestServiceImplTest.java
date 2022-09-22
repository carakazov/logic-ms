package notes.project.logic.oauth;

import notes.project.logic.oauth.client.TokenRequestClient;
import notes.project.logic.oauth.dto.InternalServerTokenResponseDto;
import notes.project.logic.oauth.impl.InternalServerOauthTokenRequestServiceImpl;
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
class InternalServerOauthTokenRequestServiceImplTest {
    @Mock
    private TokenRequestClient client;

    private OauthTokenRequestService<InternalServerTokenResponseDto> service;

    @BeforeEach
    void init() {
        service = new InternalServerOauthTokenRequestServiceImpl(
            client,
            ApplicationPropertiesUtils.applicationPropertiesForInternalServerOauthRestService()
        );
    }

    @Test
    void requestTokenSuccess() {
        InternalServerTokenResponseDto expected = IntegrationTestUtils.internalServerTokenResponseDto();

        MultiValueMap<String, String> params =  new LinkedMultiValueMap<>();
        params.add(TokenRequestParam.GRANT_TYPE.getParam(), GRANT_TYPE);
        params.add(TokenRequestParam.CLIENT_ID.getParam(), CLIENT_ID);
        params.add(TokenRequestParam.CLIENT_SECRET.getParam(), CLIENT_SECRET);

        when(client.requestToken(any(), any(), any())).thenReturn(expected);

        InternalServerTokenResponseDto actual = service.requestToken();

        assertEquals(expected, actual);

        verify(client).requestToken(INTERNAL_AUTH_URL, params, InternalServerTokenResponseDto.class);
    }
}
