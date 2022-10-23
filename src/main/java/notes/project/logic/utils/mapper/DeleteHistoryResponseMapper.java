package notes.project.logic.utils.mapper;

import notes.project.logic.dto.api.DeleteHistoryResponseDto;
import notes.project.logic.dto.integration.filesystem.FileSystemDeleteHistoryResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = DeleteHistoryRecordMapper.class)
public interface DeleteHistoryResponseMapper {
    DeleteHistoryResponseDto to(FileSystemDeleteHistoryResponseDto source);
}
