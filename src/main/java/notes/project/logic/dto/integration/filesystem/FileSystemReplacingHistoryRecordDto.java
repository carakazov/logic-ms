package notes.project.logic.dto.integration.filesystem;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class FileSystemReplacingHistoryRecordDto {
    private LocalDateTime replacingDate;
    private FileSystemDirectoryReplacingHistoryDto sourceDirectory;
    private FileSystemDirectoryReplacingHistoryDto targetDirectory;
}
