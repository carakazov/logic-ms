package notes.project.logic.utils;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TestDataConstants {
    //Common
    public static Long ID = 1L;
    public static Long ID_2 = 2L;
    public static final String EXCEPTION_CODE = "unexpectedErrorWhileCreationOperation";
    public static final String EXCEPTION_MESSAGE = "exception message";

    public static final String ACCESS_TOKEN = "access token";
    public static final String TOKEN_TYPE = "token type";
    public static final Long EXPIRES_IN = 10000L;
    public static final String SCOPE = "scope";
    public static final String JTI = "jti";
    public static final Long REFRESH_TOKEN_EXPIRES_IN = 10000L;
    public static final String REFRESH_TOKEN = "refresh token";
    public static final Integer NOT_BEFORE_POLICY = 10;
    public static final String SESSION_STATE = "session state";
    public static final String INTERNAL_AUTH_URL = "http://internal/";
    public static final String KEYCLOAK_AUTH_URL = "http://keycloak/";
    public static final String GRANT_TYPE = "grant type";
    public static final String CLIENT_ID = "client id";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String CLIENT_SECRET = "client secret";

    public static final UUID CLIENT_EXTERNAL_ID = UUID.fromString("1c7b68c7-df51-4f95-b90f-8cb9748e3635");
    public static final UUID CLUSTER_EXTERNAL_ID = UUID.fromString("e730fd34-78b9-41ab-8dd7-33c24c8fda23");
    public static final LocalDateTime CLUSTER_CREATE_DATE = LocalDateTime.of(2022, 10, 10, 10, 10, 10);
    public static final String CLUSTER_TITLE = "1c7b68c7-df51-4f95-b90f-8cb9748e3635-cluster";
}
