package notes.project.logic.utils.mapper;

import notes.project.logic.dto.api.ChangePersonalInfoRequestDto;
import notes.project.logic.dto.integration.userdatasystem.UserDataSystemChangePersonalInfoDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ChangeAdditionalInfoMapper.class)
public interface ChangePersonalInfoMapper {
    UserDataSystemChangePersonalInfoDto to(ChangePersonalInfoRequestDto source);
}
