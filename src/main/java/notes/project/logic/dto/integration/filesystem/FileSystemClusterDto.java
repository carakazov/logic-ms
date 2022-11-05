package notes.project.logic.dto.integration.filesystem;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class FileSystemClusterDto {
    private String title;
    private UUID externalId;
    private LocalDateTime creationDate;
    private List<FileSystemDirectoryInfoDto> directories;
}
