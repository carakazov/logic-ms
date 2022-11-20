package notes.project.logic.dto.integration.userdatasystem;

import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserDataSystemChangePersonalInfoDto { ;
    private Map<ChangePersonalInfoMarker, String> newValues;
    private List<UserDataSystemChangeAdditionalInfoDto> changeAdditionalInfo;
}
