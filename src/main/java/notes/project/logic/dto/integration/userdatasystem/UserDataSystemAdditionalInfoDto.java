package notes.project.logic.dto.integration.userdatasystem;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserDataSystemAdditionalInfoDto {
    private String type;
    private String value;
}
