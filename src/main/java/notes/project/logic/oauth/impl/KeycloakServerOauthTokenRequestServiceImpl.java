package notes.project.logic.oauth.impl;

import java.util.Map;

import liquibase.pro.packaged.S;
import lombok.RequiredArgsConstructor;
import notes.project.logic.config.ApplicationProperties;
import notes.project.logic.oauth.OauthTokenRequestService;
import notes.project.logic.oauth.TokenRequestParam;
import notes.project.logic.oauth.TokenSource;
import notes.project.logic.oauth.client.TokenRequestClient;
import notes.project.logic.oauth.dto.KeycloakServerTokenResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class KeycloakServerOauthTokenRequestServiceImpl implements OauthTokenRequestService<KeycloakServerTokenResponseDto> {
    private static final TokenSource SOURCE = TokenSource.KEYCLOAK_SERVER;

    private final ApplicationProperties properties;
    private final TokenRequestClient client;

    @Override
    public KeycloakServerTokenResponseDto requestToken() {
        ApplicationProperties.KeycloakServerProfile profile = properties.getKeycloakServerProfile();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(TokenRequestParam.GRANT_TYPE.getParam(), profile.getGrantType());
        params.add(TokenRequestParam.CLIENT_ID.getParam(), profile.getClientId());
        params.add(TokenRequestParam.USERNAME.getParam(), profile.getUsername());
        params.add(TokenRequestParam.PASSWORD.getParam(), profile.getPassword());
        return client.requestToken(profile.getUrl(), params, KeycloakServerTokenResponseDto.class);
    }

    @Override
    public TokenSource getSource() {
        return SOURCE;
    }
}
