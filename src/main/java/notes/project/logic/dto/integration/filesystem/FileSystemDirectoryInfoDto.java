package notes.project.logic.dto.integration.filesystem;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class FileSystemDirectoryInfoDto {
    private String title;
    private UUID externalId;
    private LocalDateTime creationDate;
}
