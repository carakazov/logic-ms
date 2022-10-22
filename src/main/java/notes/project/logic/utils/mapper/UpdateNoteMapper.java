package notes.project.logic.utils.mapper;

import notes.project.logic.dto.api.UpdateNoteRequestDto;
import notes.project.logic.dto.integration.filesystem.FileSystemUpdateFileRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UpdateNoteMapper {
    FileSystemUpdateFileRequestDto to(UpdateNoteRequestDto source);
}
