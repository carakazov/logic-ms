package notes.project.logic.dto.integration.userdatasystem;

import java.util.UUID;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserDataSystemChangePersonalInfoRequestDto {
    private UUID clientExternalId;
    private UserDataSystemChangePersonalInfoDto clientInfo;
}
