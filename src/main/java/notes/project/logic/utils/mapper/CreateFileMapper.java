package notes.project.logic.utils.mapper;

import notes.project.logic.dto.api.CreateNoteRequestDto;
import notes.project.logic.dto.api.CreateNoteResponseDto;
import notes.project.logic.dto.integration.filesystem.FileSystemCreateFileRequestDto;
import notes.project.logic.dto.integration.filesystem.FileSystemCreateFileResponseDto;
import notes.project.logic.model.Client;
import notes.project.logic.model.Directory;
import notes.project.logic.model.Note;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CreateFileMapper {
    FileSystemCreateFileRequestDto toRequest(CreateNoteRequestDto source);

    CreateNoteResponseDto toResponse(FileSystemCreateFileResponseDto source);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "externalId", source = "response.externalId")
    @Mapping(target = "client", source = "client")
    @Mapping(target = "directory", source = "directory")
    @Mapping(target = "deleted", constant = "false")
    Note toNote(CreateNoteResponseDto response, Client client, Directory directory);
}
