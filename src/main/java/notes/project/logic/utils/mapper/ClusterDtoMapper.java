package notes.project.logic.utils.mapper;

import notes.project.logic.dto.api.ClusterDto;
import notes.project.logic.dto.integration.filesystem.FileSystemClusterDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = DirectoryInfoMapper.class)
public interface ClusterDtoMapper {
    ClusterDto to(FileSystemClusterDto source);
}
