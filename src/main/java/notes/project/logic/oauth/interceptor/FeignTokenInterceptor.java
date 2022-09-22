package notes.project.logic.oauth.interceptor;

import java.util.Map;
import java.util.Set;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import liquibase.pro.packaged.T;
import lombok.RequiredArgsConstructor;
import notes.project.logic.oauth.OauthTokenRequestService;
import notes.project.logic.oauth.TokenSource;
import notes.project.logic.oauth.holder.OauthServerHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FeignTokenInterceptor implements RequestInterceptor {
    private static final String HEADER_NAME = "Authorization";
    private static final String HEADER_VALUE_TEMPLATE = "Bearer {token}";
    private static final String HEADER_TOKEN_PLACEHOLDER = "{token}";

    private final Set<OauthTokenRequestService<?>> services;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        TokenSource source = OauthServerHolder.getSource();
        OauthTokenRequestService<?> service = services.stream().filter(item -> item.getSource().equals(source)).findFirst().get();
        String token = service.requestToken().getAccessToken();
        requestTemplate.header(HEADER_NAME, HEADER_VALUE_TEMPLATE.replace(HEADER_TOKEN_PLACEHOLDER, token));
    }
}
