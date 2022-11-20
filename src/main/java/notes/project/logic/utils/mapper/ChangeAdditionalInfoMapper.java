package notes.project.logic.utils.mapper;

import java.util.List;

import notes.project.logic.dto.api.ChangeAdditionalInfoDto;
import notes.project.logic.dto.integration.userdatasystem.UserDataSystemChangeAdditionalInfoDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChangeAdditionalInfoMapper {
    UserDataSystemChangeAdditionalInfoDto to(ChangeAdditionalInfoDto source);

    List<UserDataSystemChangeAdditionalInfoDto> to(List<ChangeAdditionalInfoDto> source);
}
