package notes.project.logic.utils.mapper;

import notes.project.logic.dto.api.AccessorsListResponseDto;
import notes.project.logic.utils.mapper.dto.AccessorMappingDto;
import notes.project.logic.utils.mapper.dto.AccessorsResponseMappingDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = AccessorMapper.class)
public interface AccessorsResponseMapper {

    @Mapping(target = "externalId", source = "note.externalId")
    AccessorsListResponseDto to(AccessorsResponseMappingDto source);
}
