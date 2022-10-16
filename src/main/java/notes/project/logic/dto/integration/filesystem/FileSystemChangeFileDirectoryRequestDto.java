package notes.project.logic.dto.integration.filesystem;

import java.util.UUID;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class FileSystemChangeFileDirectoryRequestDto {
    private UUID createdFileExternalId;
    private UUID newDirectoryExternalId;
}
