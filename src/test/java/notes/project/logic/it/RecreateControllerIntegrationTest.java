package notes.project.logic.it;

import javax.inject.Inject;

import notes.project.logic.controller.DirectoryController;
import notes.project.logic.controller.RecreateController;
import notes.project.logic.utils.DbUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@Transactional
@AutoConfigureTestEntityManager
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class RecreateControllerIntegrationTest extends AbstractIntegrationTest {
    private MockMvc mockMvc;

    @Inject
    private RecreateController controller;

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
    void recreateClusterSuccess() throws Exception {
        setAuthentication(ROLE_ADMIN);
        stubKeycloakToken();

        testEntityManager.merge(DbUtils.client());

        stubFor(put(urlMatching("/recreate/cluster/e730fd34-78b9-41ab-8dd7-33c24c8fda23"))
            .willReturn(aResponse().withStatus(HttpStatus.OK.value()))
        );

        mockMvc.perform(MockMvcRequestBuilders.put("/recreate/cluster/1c7b68c7-df51-4f95-b90f-8cb9748e3635"))
            .andExpect(status().isOk());
    }

    @Test
    void recreateDirectorySuccess() throws Exception {
        setAuthentication(ROLE_ADMIN);
        stubKeycloakToken();

        testEntityManager.merge(DbUtils.client());
        testEntityManager.merge(DbUtils.directory());

        stubFor(put(urlMatching("/recreate/directory/a8314703-2bfe-46f9-ae10-9d54ed81e33f"))
            .willReturn(aResponse().withStatus(HttpStatus.OK.value()))
        );

        mockMvc.perform(MockMvcRequestBuilders.put("/recreate/directory/a8314703-2bfe-46f9-ae10-9d54ed81e33f"))
            .andExpect(status().isOk());
    }

    @Test
    void recreateNoteSuccess() throws Exception {
        setAuthentication(ROLE_ADMIN);
        stubKeycloakToken();

        testEntityManager.merge(DbUtils.client());
        testEntityManager.merge(DbUtils.directory());
        testEntityManager.merge(DbUtils.note());

        stubFor(put(urlMatching("/recreate/file/86c16469-229d-4fb6-a90e-a3a0f67dca8a"))
            .willReturn(aResponse().withStatus(HttpStatus.OK.value()))
        );

        mockMvc.perform(MockMvcRequestBuilders.put("/recreate/note/86c16469-229d-4fb6-a90e-a3a0f67dca8a"))
            .andExpect(status().isOk());
    }
}
