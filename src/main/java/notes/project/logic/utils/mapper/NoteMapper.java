package notes.project.logic.utils.mapper;

import notes.project.logic.dto.api.NoteDto;
import notes.project.logic.dto.integration.filesystem.FileSystemFileResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NoteMapper {
    NoteDto to(FileSystemFileResponseDto source);
}
