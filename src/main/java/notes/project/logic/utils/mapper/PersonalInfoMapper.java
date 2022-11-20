package notes.project.logic.utils.mapper;

import java.util.List;

import notes.project.logic.dto.api.PersonalInfoDto;
import notes.project.logic.dto.integration.userdatasystem.UserDataSystemPersonalInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = AdditionalInfoMapper.class)
public interface PersonalInfoMapper {
    PersonalInfoDto to(UserDataSystemPersonalInfoDto source);

    List<PersonalInfoDto> to(List<UserDataSystemPersonalInfoDto> source);
}
