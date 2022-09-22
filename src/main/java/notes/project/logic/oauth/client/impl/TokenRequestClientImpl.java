package notes.project.logic.oauth.client.impl;

import notes.project.logic.oauth.client.TokenRequestClient;
import notes.project.logic.oauth.dto.TokenResponseDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class TokenRequestClientImpl implements TokenRequestClient {
    @Override
    public <T extends TokenResponseDto> T requestToken(String url, MultiValueMap<String, String> params, Class<T> tokenType) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        ResponseEntity<T> responseEntity = restTemplate.postForEntity(url, request, tokenType);
        return responseEntity.getBody();
    }
}
