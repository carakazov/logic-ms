package notes.project.logic.dto.integration.filesystem;

import java.util.UUID;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class FileSystemCreateFileRequestDto {
    private String content;
    private UUID directoryExternalId;
    private String title;
}
