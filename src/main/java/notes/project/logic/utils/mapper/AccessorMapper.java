package notes.project.logic.utils.mapper;

import java.util.List;

import notes.project.logic.dto.api.AccessorDto;
import notes.project.logic.model.Access;
import notes.project.logic.utils.mapper.dto.AccessorMappingDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccessorMapper {
    @Mapping(target = "externalId", source = "user.externalId")
    @Mapping(target = "name", source = "user.name")
    @Mapping(target = "surname", source = "user.surname")
    @Mapping(target = "middleName", source = "user.middleName")
    @Mapping(target = "accessMode", source = "access.accessMode")
    AccessorDto to(AccessorMappingDto source);

    List<AccessorDto> to(List<AccessorMappingDto> source);
}
