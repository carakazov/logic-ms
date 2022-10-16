package notes.project.logic.utils.mapper;

import notes.project.logic.dto.api.MoveNoteRequestDto;
import notes.project.logic.dto.api.MoveNoteResponseDto;
import notes.project.logic.dto.integration.filesystem.FileSystemChangeFileDirectoryRequestDto;
import notes.project.logic.dto.integration.filesystem.FileSystemChangeFileDirectoryResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChangeDirectoryMapper {
    FileSystemChangeFileDirectoryRequestDto toRequest(MoveNoteRequestDto request);

    MoveNoteResponseDto toResponse(FileSystemChangeFileDirectoryResponseDto response);
}
