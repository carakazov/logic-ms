package notes.project.logic.utils.mapper;

import notes.project.logic.dto.api.NoteHistoryResponseDto;
import notes.project.logic.dto.integration.filesystem.FileSystemArchiveResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
    HistoryRecordMapper.class,
    NoteHistoryMapper.class
})
public interface NoteHistoryResponseMapper {
    @Mapping(target = "note", source = "file")
    NoteHistoryResponseDto to(FileSystemArchiveResponseDto source);
}
