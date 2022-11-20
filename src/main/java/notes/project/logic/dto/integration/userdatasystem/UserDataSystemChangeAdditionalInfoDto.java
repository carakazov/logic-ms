package notes.project.logic.dto.integration.userdatasystem;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserDataSystemChangeAdditionalInfoDto {
    private String type;
    private String newValue;
}
