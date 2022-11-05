package notes.project.logic.utils.mapper;

import notes.project.logic.dto.api.DirectoryDto;
import notes.project.logic.dto.integration.filesystem.FileSystemDirectoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = NoteInfoMapper.class)
public interface DirectoryDtoMapper {
    @Mapping(target = "notes", source = "files")
    DirectoryDto to(FileSystemDirectoryDto source);
}
