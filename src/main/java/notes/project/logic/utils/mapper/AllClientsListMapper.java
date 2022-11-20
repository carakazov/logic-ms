package notes.project.logic.utils.mapper;

import notes.project.logic.dto.api.AllClientsResponseDto;
import notes.project.logic.dto.integration.userdatasystem.UserDataSystemAllClientsResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = PersonalInfoMapper.class)
public interface AllClientsListMapper {
    AllClientsResponseDto to(UserDataSystemAllClientsResponseDto source);
}
