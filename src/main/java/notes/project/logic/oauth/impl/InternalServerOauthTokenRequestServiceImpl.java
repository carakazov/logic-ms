package notes.project.logic.oauth.impl;


import java.util.Map;

import lombok.RequiredArgsConstructor;
import notes.project.logic.config.ApplicationProperties;
import notes.project.logic.oauth.OauthTokenRequestService;
import notes.project.logic.oauth.TokenRequestParam;
import notes.project.logic.oauth.TokenSource;
import notes.project.logic.oauth.client.TokenRequestClient;
import notes.project.logic.oauth.dto.InternalServerTokenResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
@RequiredArgsConstructor
public class InternalServerOauthTokenRequestServiceImpl implements OauthTokenRequestService<InternalServerTokenResponseDto> {
    private static final TokenSource SOURCE = TokenSource.INTERNAL_SERVER;

    private final TokenRequestClient client;
    private final ApplicationProperties properties;

    @Override
    public InternalServerTokenResponseDto requestToken() {
        ApplicationProperties.InternalServerProfile profile = properties.getInternalServerProfile();
        MultiValueMap<String, String> params =  new LinkedMultiValueMap<>();
        params.add(TokenRequestParam.GRANT_TYPE.getParam(), profile.getGrantType());
        params.add(TokenRequestParam.CLIENT_ID.getParam(), profile.getClientId());
        params.add(TokenRequestParam.CLIENT_SECRET.getParam(), profile.getClientSecret());
        return client.requestToken(profile.getUrl(), params, InternalServerTokenResponseDto.class);
    }

    @Override
    public TokenSource getSource() {
        return SOURCE;
    }
}
