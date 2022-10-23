package notes.project.logic.utils.mapper;

import notes.project.logic.dto.api.NoteHistoryDto;
import notes.project.logic.dto.integration.filesystem.FileSystemFileDto;
import notes.project.logic.dto.integration.filesystem.FileSystemHistoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NoteHistoryMapper {
    @Mapping(target = "noteExternalId", source = "fileExternalId")
    @Mapping(target = "noteTitle", source = "fileTitle")
    NoteHistoryDto to(FileSystemFileDto source);
}
