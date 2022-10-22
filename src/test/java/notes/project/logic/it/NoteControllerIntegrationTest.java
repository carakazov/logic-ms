package notes.project.logic.it;

import javax.inject.Inject;

import liquibase.pro.packaged.D;
import notes.project.logic.controller.DirectoryController;
import notes.project.logic.controller.NoteController;
import notes.project.logic.model.Note;
import notes.project.logic.utils.DbUtils;
import notes.project.logic.utils.IntegrationTestUtils;
import notes.project.logic.utils.TestUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.DataValidationException;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@Transactional
@AutoConfigureTestEntityManager
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class NoteControllerIntegrationTest extends AbstractIntegrationTest {
    private MockMvc mockMvc;

    @Inject
    private NoteController controller;

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
    void createNoteSuccess() throws Exception {
        setAuthentication(ROLE_USER);
        stubKeycloakToken();

        testEntityManager.merge(DbUtils.client());
        testEntityManager.merge(DbUtils.directory());

        stubFor(post(urlMatching("/file"))
            .willReturn(aResponse()
                .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .withBody(TestUtils.getClasspathResource("/integration/filesystem/CreateFileFileSystemResponse.json"))
                .withStatus(HttpStatus.OK.value())));

        String expected = TestUtils.getClasspathResource("/api/CreateNoteResponse.json");

        String actual = mockMvc.perform(MockMvcRequestBuilders.post("/note")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtils.getClasspathResource("/api/CreateNoteRequest.json")))
            .andReturn().getResponse().getContentAsString();

        JSONAssert.assertEquals(expected, actual, true);

        Note note = testEntityManager.getEntityManager().createQuery(
            "select note from notes note",
            Note.class
        ).getSingleResult();

        assertNotNull(note);
    }

    @Test
    void moveDirectorySuccess() throws Exception {
        setAuthentication(ROLE_USER);
        stubKeycloakToken();

        testEntityManager.merge(DbUtils.client());
        testEntityManager.merge(DbUtils.directory());
        testEntityManager.merge(DbUtils.alternateDirectory());
        testEntityManager.merge(DbUtils.note());

        stubFor(put(urlMatching("/file"))
            .willReturn(aResponse()
                .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .withBody(TestUtils.getClasspathResource("/integration/filesystem/ChangeFileDirectoryResponse.json"))
                .withStatus(HttpStatus.OK.value())));

        String expected = TestUtils.getClasspathResource("/api/MoveNoteResponse.json");

        String actual = mockMvc.perform(MockMvcRequestBuilders.put("/note")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtils.getClasspathResource("/api/MoveNoteRequest.json")))
            .andReturn().getResponse().getContentAsString();

        JSONAssert.assertEquals(expected, actual, true);

        Note note = testEntityManager.getEntityManager().createQuery(
            "select note from notes note",
            Note.class
        ).getSingleResult();

        assertEquals(DbUtils.alternateDirectory(), note.getDirectory());
    }

    @Test
    void readNoteSuccess() throws Exception {
        setAuthentication(ROLE_USER);
        stubKeycloakToken();

        testEntityManager.merge(DbUtils.client());
        testEntityManager.merge(DbUtils.directory());
        testEntityManager.merge(DbUtils.note());
        testEntityManager.merge(DbUtils.access());

        stubFor(get(urlMatching("/file/86c16469-229d-4fb6-a90e-a3a0f67dca8a"))
            .willReturn(aResponse()
                .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .withBody(TestUtils.getClasspathResource("/integration/filesystem/ReadFileResponse.json"))
                .withStatus(HttpStatus.OK.value())
            )
        );

        String expected = TestUtils.getClasspathResource("/api/ReadNoteResponse.json");

        String actual = mockMvc.perform(MockMvcRequestBuilders.get("/note/86c16469-229d-4fb6-a90e-a3a0f67dca8a"))
            .andReturn().getResponse().getContentAsString();

        JSONAssert.assertEquals(expected, actual, true);
    }

    @Test
    void updateNoteSuccess() throws Exception {
        setAuthentication(ROLE_USER);
        stubKeycloakToken();

        testEntityManager.merge(DbUtils.client());
        testEntityManager.merge(DbUtils.directory());
        testEntityManager.merge(DbUtils.note());
        testEntityManager.merge(DbUtils.access());

        stubFor(put(urlMatching("/file/86c16469-229d-4fb6-a90e-a3a0f67dca8a"))
            .willReturn(aResponse().withStatus(HttpStatus.OK.value())));

        mockMvc.perform(MockMvcRequestBuilders.put("/note/86c16469-229d-4fb6-a90e-a3a0f67dca8a")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.getClasspathResource("/api/UpdateNoteRequest.json")))
            .andExpect(status().isOk());
    }
}
