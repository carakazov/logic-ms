package notes.project.logic.utils.mapper;

import java.util.List;

import notes.project.logic.dto.api.NoteInfoDto;
import notes.project.logic.dto.integration.filesystem.FileSystemFileInfoDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NoteInfoMapper {
    NoteInfoDto to(FileSystemFileInfoDto source);

    List<NoteInfoDto> to(List<FileSystemFileInfoDto> source);
}
