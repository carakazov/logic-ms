package notes.project.logic.it;

import java.io.IOException;
import javax.inject.Inject;

import com.github.tomakehurst.wiremock.http.HttpHeader;
import com.sun.jdi.event.ExceptionEvent;
import notes.project.logic.controller.ClientController;
import notes.project.logic.dto.api.ClusterDto;
import notes.project.logic.utils.DbUtils;
import notes.project.logic.utils.TestUtils;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static notes.project.logic.utils.TestDataConstants.CLUSTER_CREATE_DATE;
import static notes.project.logic.utils.TestDataConstants.CLUSTER_TITLE;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@Transactional
@AutoConfigureTestEntityManager
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ClientControllerIntegrationTest extends AbstractIntegrationTest {
    private MockMvc mockMvc;

    @Inject
    private ClientController controller;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
            .setMessageConverters(new MappingJackson2HttpMessageConverter(objectMapper))
            .build();
        wireMock.start();
        configureFor("localhost", wireMock.port());
    }

    @AfterEach
    void tearDown() {
        wireMock.shutdown();
    }

    @Test
    void getPersonalInfoSuccess() throws Exception {
        setAuthentication(ROLE_USER);

        testEntityManager.merge(DbUtils.client());

        stubInternalToken();

        stubFor(get(urlMatching("/client/1c7b68c7-df51-4f95-b90f-8cb9748e3635"))
            .willReturn(aResponse()
                .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .withBody(TestUtils.getClasspathResource("/integration/userdatasystem/UserDataResponse.json"))
                .withStatus(HttpStatus.OK.value())
            )
        );

        String actual = mockMvc.perform(MockMvcRequestBuilders.get("/client/1c7b68c7-df51-4f95-b90f-8cb9748e3635"))
            .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        String expected = TestUtils.getClasspathResource("/api/PersonalInfoResponse.json");

        JSONAssert.assertEquals(expected, actual, true);
    }

    @Test
    void readClusterSuccess() throws Exception {
        setAuthentication(ROLE_USER);
        stubKeycloakToken();

        testEntityManager.merge(DbUtils.client());

        stubFor(get(urlMatching("/cluster/e730fd34-78b9-41ab-8dd7-33c24c8fda23"))
            .willReturn(aResponse()
                .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .withBody(TestUtils.getClasspathResource("/integration/filesystem/FileSystemClusterResponse.json"))
                .withStatus(HttpStatus.OK.value())
            )
        );

        String actual = mockMvc.perform(MockMvcRequestBuilders.get("/client"))
            .andReturn().getResponse().getContentAsString();

        String expected = TestUtils.getClasspathResource("/api/ClusterResponse.json");

        JSONAssert.assertEquals(expected, actual, true);
    }

    @Test
    void deleteClusterSuccess() throws Exception {
        setAuthentication(ROLE_USER);
        stubKeycloakToken();

        testEntityManager.merge(DbUtils.client());

        stubFor(delete(urlMatching("/cluster/e730fd34-78b9-41ab-8dd7-33c24c8fda23"))
            .willReturn(aResponse()
                .withStatus(HttpStatus.OK.value())
            )
        );

        mockMvc.perform(MockMvcRequestBuilders.delete("/client")).andExpect(status().isOk());
    }

    @Test
    void getClusterDeleteHistorySuccess() throws Exception {
        setAuthentication(ROLE_ADMIN);
        stubKeycloakToken();

        testEntityManager.merge(DbUtils.client());

        stubFor(get(urlMatching("/cluster/e730fd34-78b9-41ab-8dd7-33c24c8fda23/deleteHistory"))
            .willReturn(aResponse()
                .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .withBody(TestUtils.getClasspathResource("/integration/filesystem/FileSystemDeleteHistoryResponse.json")
                    .replace(OBJECT_TITLE_PLACEHOLDER, CLUSTER_TITLE)
                    .replace(CREATED_DATE_PLACEHOLDER, CLUSTER_CREATE_DATE.toString())
                )
                .withStatus(HttpStatus.OK.value())
            )
        );

        String expected = TestUtils.getClasspathResource("/api/DeleteHistoryResponse.json")
            .replace(OBJECT_TITLE_PLACEHOLDER, CLUSTER_TITLE)
            .replace(CREATED_DATE_PLACEHOLDER, CLUSTER_CREATE_DATE.toString());

        String actual = mockMvc.perform(MockMvcRequestBuilders.get("/client/1c7b68c7-df51-4f95-b90f-8cb9748e3635/deleteHistory"))
            .andReturn().getResponse().getContentAsString();

        JSONAssert.assertEquals(expected, actual, true);
    }

    @Test
    void changePersonalInfoSuccess() throws Exception {
        setAuthentication(ROLE_USER);
        stubInternalToken();

        testEntityManager.merge(DbUtils.client());

        stubFor(put(urlMatching("/client"))
            .willReturn(aResponse()
                .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .withBody(TestUtils.getClasspathResource("/integration/userdatasystem/UserDataResponse.json"))
                .withStatus(HttpStatus.OK.value())
            )
        );

        String expected = TestUtils.getClasspathResource("/integration/userdatasystem/UserDataResponse.json");

        String actual = mockMvc.perform(MockMvcRequestBuilders.put("/client")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtils.getClasspathResource("/api/ChangePersonalInfoRequest.json")
            )
        ).andReturn().getResponse().getContentAsString();

        JSONAssert.assertEquals(expected, actual, true);
    }

    @Test
    void getAllClientsSuccess() throws Exception {
        setAuthentication(ROLE_ADMIN);
        stubInternalToken();

        stubFor(get(urlMatching("/clients/test-system-name/list"))
            .willReturn(aResponse()
                .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .withBody(TestUtils.getClasspathResource("/integration/userdatasystem/AllClientsSystem.json"))
                .withStatus(HttpStatus.OK.value())
            )
        );

        String expected = TestUtils.getClasspathResource("/api/AllClientsResponse.json");

        String actual = mockMvc.perform(MockMvcRequestBuilders.get("/client/list"))
            .andReturn().getResponse().getContentAsString();

        JSONAssert.assertEquals(expected, actual, true);
    }
}
