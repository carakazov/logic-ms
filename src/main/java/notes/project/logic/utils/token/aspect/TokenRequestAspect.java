package notes.project.logic.utils.token.aspect;

import notes.project.logic.oauth.holder.OauthServerHolder;
import notes.project.logic.utils.token.TokenRequest;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TokenRequestAspect {

    @Before(value = "@annotation(tokenRequest)", argNames = "tokenRequest")
    public void setTokenSource(TokenRequest tokenRequest) {
        OauthServerHolder.setSource(tokenRequest.tokenSource());
    }
}
