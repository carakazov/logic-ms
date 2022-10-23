package notes.project.logic.utils.mapper;

import notes.project.logic.dto.api.NoteVersionResponseDto;
import notes.project.logic.dto.integration.filesystem.FileSystemFileVersionDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NoteVersionMapper {
    NoteVersionResponseDto to(FileSystemFileVersionDto source);
}
