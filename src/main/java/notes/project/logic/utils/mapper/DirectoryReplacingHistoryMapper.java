package notes.project.logic.utils.mapper;

import notes.project.logic.dto.api.DirectoryReplacingHistoryDto;
import notes.project.logic.dto.integration.filesystem.FileSystemDirectoryReplacingHistoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DirectoryReplacingHistoryMapper {
    DirectoryReplacingHistoryDto to(FileSystemDirectoryReplacingHistoryDto source);
}
