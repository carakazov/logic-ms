package notes.project.logic.utils.mapper;

import notes.project.logic.dto.api.NoteReplacingHistoryDto;
import notes.project.logic.dto.api.NoteReplacingHistoryResponseDto;
import notes.project.logic.dto.integration.filesystem.FileSystemReplacingHistoryResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
    NoteReplacingHistoryMapper.class,
    ReplacingHistoryRecordMapper.class
})
public interface ReplacingHistoryResponseMapper {
    @Mapping(target = "note", source = "file")
    NoteReplacingHistoryResponseDto to(FileSystemReplacingHistoryResponseDto source);
}
