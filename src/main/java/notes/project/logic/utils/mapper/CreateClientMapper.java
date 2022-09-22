package notes.project.logic.utils.mapper;

import java.util.UUID;

import notes.project.logic.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CreateClientMapper {
    @Mapping(target = "externalId", source = "clientExternalId")
    @Mapping(target = "clusterExternalId", source = "clusterExternalId")
    Client to(UUID clientExternalId, UUID clusterExternalId);
}
