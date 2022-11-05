package notes.project.logic.utils.mapper;

import java.util.List;

import notes.project.logic.dto.api.DirectoryInfoDto;
import notes.project.logic.dto.integration.filesystem.FileSystemDirectoryInfoDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DirectoryInfoMapper {
    DirectoryInfoDto to(FileSystemDirectoryInfoDto source);

    List<DirectoryInfoDto> to(List<FileSystemDirectoryInfoDto> source);
}
