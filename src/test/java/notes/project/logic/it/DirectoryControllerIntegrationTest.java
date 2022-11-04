package notes.project.logic.it;

import javax.inject.Inject;

import liquibase.pro.packaged.D;
import notes.project.logic.controller.ClientController;
import notes.project.logic.controller.DirectoryController;
import notes.project.logic.model.Directory;
import notes.project.logic.utils.DbUtils;
import notes.project.logic.utils.TestUtils;
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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyChar;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static notes.project.logic.utils.TestDataConstants.*;

@ExtendWith(SpringExtension.class)
@Transactional
@AutoConfigureTestEntityManager
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class DirectoryControllerIntegrationTest extends AbstractIntegrationTest {
    private MockMvc mockMvc;

    @Inject
    private DirectoryController controller;

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
    void createDirectorySuccess() throws Exception{
        setAuthentication(ROLE_USER);

        testEntityManager.merge(DbUtils.client());
        stubKeycloakToken();

        stubFor(post(urlMatching("/directory"))
            .willReturn(aResponse()
                .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .withBody(TestUtils.getClasspathResource("/integration/filesystem/CreateDirectoryFileSystemResponse.json"))
                .withStatus(HttpStatus.OK.value()))
        );

        String actual = mockMvc.perform(MockMvcRequestBuilders.post("/directory")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.getClasspathResource("/api/CreateDirectoryRequest.json")))
            .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        String expected = TestUtils.getClasspathResource("/api/CreateDirectoryResponse.json");

        JSONAssert.assertEquals(expected, actual, true);

        Directory directory = testEntityManager.getEntityManager().createQuery(
            "select directory from directories directory",
            Directory.class
        ).getSingleResult();

        assertNotNull(directory);
    }

    @Test
    void deleteDirectorySuccess() throws Exception {
        setAuthentication(ROLE_USER);
        stubKeycloakToken();

        testEntityManager.merge(DbUtils.client());
        testEntityManager.merge(DbUtils.directory());

        stubFor(delete(urlMatching("/directory/a8314703-2bfe-46f9-ae10-9d54ed81e33f"))
            .willReturn(aResponse()
                .withStatus(HttpStatus.OK.value())));

        mockMvc.perform(MockMvcRequestBuilders.delete("/directory/a8314703-2bfe-46f9-ae10-9d54ed81e33f"))
            .andExpect(status().isOk());
    }

    @Test
    void readDirectorySuccess() throws Exception {
        setAuthentication(ROLE_USER);
        stubKeycloakToken();

        testEntityManager.merge(DbUtils.client());
        testEntityManager.merge(DbUtils.directory());

        stubFor(get(urlMatching("/directory/a8314703-2bfe-46f9-ae10-9d54ed81e33f"))
            .willReturn(aResponse()
                .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .withBody(TestUtils.getClasspathResource("/integration/filesystem/FileSystemReadDirectoryResponse.json"))
                .withStatus(HttpStatus.OK.value())
            )
        );

        String expected = TestUtils.getClasspathResource("/api/ReadDirectoryResponse.json");

        String actual = mockMvc.perform(MockMvcRequestBuilders.get("/directory/a8314703-2bfe-46f9-ae10-9d54ed81e33f"))
            .andExpect(status().isOk())
            .andReturn().getResponse().getContentAsString();

        JSONAssert.assertEquals(expected, actual, true);
    }

    @Test
    void getDirectoryDeleteHistory() throws Exception {
        setAuthentication(ROLE_ADMIN);
        stubKeycloakToken();

        testEntityManager.merge(DbUtils.client());
        testEntityManager.merge(DbUtils.directory());

        stubFor(get(urlMatching("/directory/a8314703-2bfe-46f9-ae10-9d54ed81e33f/deleteHistory"))
            .willReturn(aResponse()
                .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .withBody(TestUtils.getClasspathResource("/integration/filesystem/FileSystemDeleteHistoryResponse.json")
                    .replace(OBJECT_TITLE_PLACEHOLDER, DIRECTORY_NAME)
                    .replace(CREATED_DATE_PLACEHOLDER, DIRECTORY_CREATION_TIME.toString())
                )
            )
        );

        String expected = TestUtils.getClasspathResource("/api/DeleteHistoryResponse.json")
            .replace(OBJECT_TITLE_PLACEHOLDER, DIRECTORY_NAME)
            .replace(CREATED_DATE_PLACEHOLDER, DIRECTORY_CREATION_TIME.toString());


        String actual = mockMvc.perform(MockMvcRequestBuilders.get("/directory/a8314703-2bfe-46f9-ae10-9d54ed81e33f/deleteHistory"))
            .andReturn().getResponse().getContentAsString();

        JSONAssert.assertEquals(expected, actual, true);
    }
}
