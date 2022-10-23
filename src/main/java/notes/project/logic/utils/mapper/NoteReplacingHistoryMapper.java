package notes.project.logic.utils.mapper;

import notes.project.logic.dto.api.NoteReplacingHistoryDto;
import notes.project.logic.dto.integration.filesystem.FileSystemFileDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NoteReplacingHistoryMapper {
    @Mapping(target = "noteTitle", source = "fileTitle")
    @Mapping(target = "noteExternalId", source = "fileExternalId")
    NoteReplacingHistoryDto to(FileSystemFileDto source);
}
