package notes.project.logic.it;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.fasterxml.jackson.databind.ObjectMapper;
import notes.project.logic.config.ApplicationProperties;
import notes.project.logic.utils.TestAsyncTaskExecutor;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
                classes = AbstractIntegrationTest.IntegrationTestConfiguration.class)
@AutoConfigureJsonTesters
@ActiveProfiles("it")
public abstract class AbstractIntegrationTest {

    @Inject
    protected ApplicationProperties applicationProperties;
    @Inject
    protected ObjectMapper objectMapper;


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
