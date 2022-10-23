package notes.project.logic.dto.integration.filesystem;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class FileSystemDeleteHistoryResponseDto {
    private String objectTitle;
    private String currentState;
    private LocalDateTime createdDate;

    private List<FileSystemDeleteHistoryRecordDto> history;
}
