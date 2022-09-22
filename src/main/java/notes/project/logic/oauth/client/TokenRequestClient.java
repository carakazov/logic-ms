package notes.project.logic.oauth.client;

import java.util.Map;

import notes.project.logic.oauth.TokenRequestParam;
import notes.project.logic.oauth.dto.TokenResponseDto;
import org.springframework.util.MultiValueMap;

public interface TokenRequestClient {
    <T extends TokenResponseDto> T requestToken(String url, MultiValueMap<String, String> params, Class<T> tokenType);
}
