package notes.project.logic.utils.mapper;

import java.util.List;

import notes.project.logic.dto.api.NoteHistoryRecordDto;
import notes.project.logic.dto.integration.filesystem.FileSystemHistoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HistoryRecordMapper {
    @Mapping(target = "versionNoteGuid", source = "versionFileGuid")
    NoteHistoryRecordDto to(FileSystemHistoryDto source);

    List<NoteHistoryRecordDto> to(List<FileSystemHistoryDto> source);
}
