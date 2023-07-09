package notes.project.logic.utils.mapper;

import notes.project.logic.dto.api.MoveNoteRequestDto;
import notes.project.logic.dto.api.MoveNoteResponseDto;
import notes.project.logic.dto.integration.filesystem.FileSystemChangeFileDirectoryRequestDto;
import notes.project.logic.dto.integration.filesystem.FileSystemChangeFileDirectoryResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChangeDirectoryMapper {
    @Mapping(target = "createdFileExternalId", source = "createdNoteExternalId")
    FileSystemChangeFileDirectoryRequestDto toRequest(MoveNoteRequestDto request);

    MoveNoteResponseDto toResponse(FileSystemChangeFileDirectoryResponseDto response);
}
