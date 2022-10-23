package notes.project.logic.dto.integration.filesystem;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class FileSystemDeleteHistoryRecordDto {
    private String event;
    private LocalDateTime eventDate;
}