package notes.project.logic.utils.mapper;

import java.util.List;

import notes.project.logic.dto.api.DeleteHistoryRecordDto;
import notes.project.logic.dto.integration.filesystem.FileSystemDeleteHistoryRecordDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DeleteHistoryRecordMapper {
    DeleteHistoryRecordDto to(FileSystemDeleteHistoryRecordDto source);

    List<DeleteHistoryRecordDto> to(List<FileSystemDeleteHistoryRecordDto> source);
}
