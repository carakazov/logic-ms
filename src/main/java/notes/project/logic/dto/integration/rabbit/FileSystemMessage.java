package notes.project.logic.dto.integration.rabbit;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class FileSystemMessage {
    private EventCode eventCode;
    private UUID clusterExternalId;
    private Long daysBeforeDelete;

    @JsonCreator
    public FileSystemMessage(@JsonProperty("eventCode") String eventCode, @JsonProperty("clusterExternalId") UUID clusterExternalId, @JsonProperty("daysBeforeDelete") Long daysBeforeDelete) {
        this.eventCode = EventCode.getByCode(eventCode);
        this.clusterExternalId = clusterExternalId;
        this.daysBeforeDelete = daysBeforeDelete;
    }
}
