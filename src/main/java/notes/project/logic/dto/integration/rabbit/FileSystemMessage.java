package notes.project.logic.dto.integration.rabbit;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class FileSystemMessage {
    private EventCode eventCode;
    private UUID clusterExternalId;
    private Long daysBeforeDelete;
}
