package notes.project.logic.utils.mapper;

import java.util.List;

import notes.project.logic.dto.api.AdditionalInfoDto;
import notes.project.logic.dto.integration.userdatasystem.UserDataSystemAdditionalInfoDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdditionalInfoMapper {
    AdditionalInfoDto to(UserDataSystemAdditionalInfoDto source);

    List<AdditionalInfoDto> to(List<UserDataSystemAdditionalInfoDto> source);
}
