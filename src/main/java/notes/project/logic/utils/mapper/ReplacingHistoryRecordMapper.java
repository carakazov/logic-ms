package notes.project.logic.utils.mapper;

import java.util.List;

import notes.project.logic.dto.api.ReplacingHistoryRecordDto;
import notes.project.logic.dto.integration.filesystem.FileSystemReplacingHistoryRecordDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = DirectoryReplacingHistoryMapper.class)
public interface ReplacingHistoryRecordMapper {
    ReplacingHistoryRecordDto to(FileSystemReplacingHistoryRecordDto source);

    List<ReplacingHistoryRecordDto> to(List<FileSystemReplacingHistoryRecordDto> source);
}
