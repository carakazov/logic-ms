package notes.project.logic.it;

import java.time.Instant;
import java.util.Collections;
import java.util.Map;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import liquibase.pro.packaged.E;
import notes.project.logic.config.ApplicationProperties;
import notes.project.logic.utils.AuthHelper;
import notes.project.logic.utils.TestAsyncTaskExecutor;
import notes.project.logic.utils.TestUtils;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.test.context.ActiveProfiles;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static notes.project.logic.utils.TestDataConstants.CLIENT_EXTERNAL_ID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
                classes = AbstractIntegrationTest.IntegrationTestConfiguration.class)
@AutoConfigureJsonTesters
@ActiveProfiles("it")
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:29092", "port=29092"})
public abstract class AbstractIntegrationTest {

    private static final String TOKEN_VALUE = "token value";
    private static final Instant ISSUED_AT = Instant.now();
    private static final Instant EXPIRES_IN = Instant.now().plusSeconds(300);
    private static final String HEADER_KEY = "header key";
    private static final String HEADER_VALUE = "header value";
    private static final String EXTERNAL_ID_CLAIM = "externalId";

    protected static final String ROLE_USER = "ROLE_USER";
    protected static final String ROLE_ADMIN = "ROLE_ADMIN";

    @Inject
    protected ApplicationProperties applicationProperties;
    @Inject
    protected ObjectMapper objectMapper;

    protected WireMockServer wireMock = new WireMockServer(23845);

    protected void setAuthentication(String role) {
        Jwt jwt = new Jwt(
            TOKEN_VALUE,
            ISSUED_AT,
            EXPIRES_IN,
            Map.of(HEADER_KEY, HEADER_VALUE),
            Map.of(EXTERNAL_ID_CLAIM, CLIENT_EXTERNAL_ID)
        );
        Authentication authentication = new JwtAuthenticationToken(jwt, Collections.singletonList(new SimpleGrantedAuthority(role)));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    protected void stubKeycloakToken() throws Exception {
        stubFor(post(urlMatching("/auth/realms/file-system-realm/protocol/openid-connect/token"))
            .willReturn(aResponse()
                .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .withBody(TestUtils.getClasspathResource("/integration/tokens/KeycloakToken.json"))
                .withStatus(HttpStatus.OK.value())
            )
        );
    }

    protected void stubInternalToken() throws Exception {
        stubFor(post(urlMatching("/oauth/token"))
            .willReturn(aResponse()
                .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .withBody(TestUtils.getClasspathResource("/integration/tokens/InternalSystemToken.json"))
                .withStatus(HttpStatus.OK.value())
            )
        );
    }

    @ActiveProfiles("it")
    @TestConfiguration
    public static class IntegrationTestConfiguration {
        @Bean("asyncTaskExecutor")
        public TaskExecutor getTaskExecutor(EntityManager entityManager) {
            return new TestAsyncTaskExecutor(entityManager);
        }
    }

    @Inject
    protected TestEntityManager testEntityManager;

}
