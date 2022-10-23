package notes.project.logic.dto.integration.filesystem;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class FileSystemReplacingHistoryResponseDto {
    private FileSystemFileDto file;
    private List<FileSystemReplacingHistoryRecordDto> history;
}
