package notes.project.logic.dto.integration.filesystem;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class FileSystemCreateDirectoryResponseDto {
    private String clusterName;
    private LocalDateTime creationDate;
    private String directoryName;
    private UUID externalId;
}
