package notes.project.logic.utils;

import java.time.LocalDate;
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

    public static final String CLIENT_ADDITIONAL_INFO_TYPE = "type";
    public static final String CLIENT_ADDITIONAL_INFO_VALUE = "value";
    public static final String CLIENT_NAME = "name";
    public static final String CLIENT_SURNAME = "surname";
    public static final String CLIENT_MIDDLE_NAME = "middle name";
    public static final LocalDate CLIENT_BIRTH_DATE = LocalDate.of(2000, 10, 10);

    public static final String DIRECTORY_NAME = "dir name";
    public static final LocalDateTime DIRECTORY_CREATION_TIME = LocalDateTime.of(2022, 10, 10, 10, 10, 10);
    public static final String DIRECTORY_EXTERNAL_ID_AS_STRING = "a8314703-2bfe-46f9-ae10-9d54ed81e33f";
    public static final UUID DIRECTORY_EXTERNAL_ID = UUID.fromString(DIRECTORY_EXTERNAL_ID_AS_STRING);

    public static final String NOTE_CONTENT = "test content";
    public static final String NOTE_TITLE = "note title";

    public static final LocalDateTime NOTE_CREATED_DATE = LocalDateTime.of(2022, 10, 10, 10, 10,10);
    public static final String NOTE_EXTERNAL_ID_AS_STRING = "86c16469-229d-4fb6-a90e-a3a0f67dca8a";
    public static final UUID NOTE_EXTERNAL_ID = UUID.fromString(NOTE_EXTERNAL_ID_AS_STRING);

    public static final String DIRECTORY_ALTERNATE_EXTERNAL_ID_AS_STRING = "3341d5bb-e69c-4db8-a603-e500f6c54e97";
    public static final UUID DIRECTORY_ALTERNATE_EXTERNAL_ID = UUID.fromString(DIRECTORY_ALTERNATE_EXTERNAL_ID_AS_STRING);
    public static final LocalDateTime NOTE_MOVING_DATE = LocalDateTime.of(2022, 10, 10, 12, 12, 12);

    public static final String NEW_NOTE_CONTENT = "updated content";

    public static final UUID VERSION_FILE_GUID = UUID.fromString("bc281f22-9b44-4eff-9a40-582927ec4513");
    public static final LocalDateTime EDITED_DATE = LocalDateTime.of(2022, 10, 10, 12, 12, 12);

    public static final String CURRENT_STATE = "CREATED";
    public static final String DELETE_HISTORY_EVENT = "CREATED";
    public static final LocalDateTime DELETE_HISTORY_EVENT_DATE = LocalDateTime.of(2022, 10, 10, 10, 10 ,10);
}
