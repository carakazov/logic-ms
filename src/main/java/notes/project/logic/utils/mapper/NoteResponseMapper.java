package notes.project.logic.utils.mapper;

import notes.project.logic.dto.api.NoteResponseDto;
import notes.project.logic.dto.integration.filesystem.FileSystemFileResponseDto;
import notes.project.logic.model.Access;
import notes.project.logic.model.AccessMode;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = NoteMapper.class)
public interface NoteResponseMapper {

    @Mapping(target = "note", source = "response")
    @Mapping(target = "accessMode", source = "access.accessMode")
    NoteResponseDto to(FileSystemFileResponseDto response, Access access);
}
