package notes.project.logic.oauth;

import notes.project.logic.oauth.dto.TokenResponseDto;

public interface OauthTokenRequestService<T extends TokenResponseDto> {
    T requestToken();

    TokenSource getSource();
}
