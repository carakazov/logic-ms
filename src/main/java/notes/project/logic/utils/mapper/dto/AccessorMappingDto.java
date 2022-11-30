package notes.project.logic.utils.mapper.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import notes.project.logic.dto.integration.userdatasystem.UserDataSystemPersonalInfoDto;
import notes.project.logic.model.Access;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessorMappingDto {
    private UserDataSystemPersonalInfoDto user;
    private Access access;
}
