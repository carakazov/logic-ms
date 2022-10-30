package notes.project.logic.utils.mapper;

import notes.project.logic.dto.api.DirectoryInfoDto;
import notes.project.logic.dto.integration.filesystem.FileSystemDirectoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = NoteInfoMapper.class)
public interface DirectoryInfoMapper {
    @Mapping(target = "notes", source = "files")
    DirectoryInfoDto to(FileSystemDirectoryDto source);
}
