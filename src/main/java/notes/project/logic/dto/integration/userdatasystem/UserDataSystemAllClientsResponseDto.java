package notes.project.logic.dto.integration.userdatasystem;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserDataSystemAllClientsResponseDto {
    private String systemName;
    private List<UserDataSystemPersonalInfoDto> clients;
}
