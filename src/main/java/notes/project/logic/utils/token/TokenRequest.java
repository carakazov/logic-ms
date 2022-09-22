package notes.project.logic.utils.token;

import java.lang.annotation.*;

import notes.project.logic.oauth.TokenSource;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TokenRequest {
    TokenSource tokenSource() default TokenSource.KEYCLOAK_SERVER;
}
