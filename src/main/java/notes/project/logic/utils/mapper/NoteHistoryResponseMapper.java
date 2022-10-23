package notes.project.logic.utils.mapper;

import notes.project.logic.dto.api.NoteHistoryResponseDto;
import notes.project.logic.dto.integration.filesystem.FileSystemArchiveResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
    HistoryRecordMapper.class,
    NoteHistoryMapper.class
})
public interface NoteHistoryResponseMapper {
    NoteHistoryResponseDto to(FileSystemArchiveResponseDto source);
}
