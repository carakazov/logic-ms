package notes.project.logic.utils.mapper;

import java.util.UUID;

import liquibase.pro.packaged.M;
import notes.project.logic.dto.api.CreateDirectoryRequestDto;
import notes.project.logic.dto.api.CreateDirectoryResponseDto;
import notes.project.logic.dto.integration.filesystem.CreateClusterRequestDto;
import notes.project.logic.dto.integration.filesystem.FileSystemCreateDirectoryRequestDto;
import notes.project.logic.dto.integration.filesystem.FileSystemCreateDirectoryResponseDto;
import notes.project.logic.model.Client;
import notes.project.logic.model.Directory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CreateDirectoryMapper {
    @Mapping(target = "directoryName", source = "directoryName")
    @Mapping(target = "clusterExternalId", source = "clusterExternalId")
    FileSystemCreateDirectoryRequestDto toRequest(String directoryName, UUID clusterExternalId);

    CreateDirectoryResponseDto toResponse(FileSystemCreateDirectoryResponseDto source);

    @Mapping(target = "client", source = "client")
    @Mapping(target = "externalId", source = "response.externalId")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", constant = "false")
    @Mapping(target = "title", source = "response.directoryName")
    Directory toDirectory(Client client, CreateDirectoryResponseDto response);
}
