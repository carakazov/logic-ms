package notes.project.logic.dto.integration.filesystem;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class FileSystemFileVersionDto {
    private String content;
}
