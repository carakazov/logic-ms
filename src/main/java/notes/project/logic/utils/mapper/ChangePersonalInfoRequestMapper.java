package notes.project.logic.utils.mapper;

import notes.project.logic.utils.mapper.dto.ChangePersonalInfoMappingDto;
import notes.project.logic.dto.integration.userdatasystem.UserDataSystemChangePersonalInfoRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ChangePersonalInfoMapper.class)
public interface ChangePersonalInfoRequestMapper {
    UserDataSystemChangePersonalInfoRequestDto to(ChangePersonalInfoMappingDto source);
}
