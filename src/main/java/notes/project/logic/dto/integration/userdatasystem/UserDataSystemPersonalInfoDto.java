package notes.project.logic.dto.integration.userdatasystem;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserDataSystemPersonalInfoDto {
    private String name;
    private String surname;
    private String middleName;
    private LocalDate birthDate;
    private UUID externalId;
    private List<UserDataSystemAdditionalInfoDto> additionalInfo;
}
