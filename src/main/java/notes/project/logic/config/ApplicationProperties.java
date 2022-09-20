package notes.project.logic.config;

import java.util.Map;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "application")
@Component
@Accessors(chain = true)
public class ApplicationProperties {

    private Map<String, String> errorMessages;

    public String getMessage(String messageCode) {
        return errorMessages.get(messageCode);
    }
}
