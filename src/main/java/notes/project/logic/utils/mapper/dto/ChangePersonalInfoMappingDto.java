package notes.project.logic.utils.mapper.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import notes.project.logic.dto.api.ChangePersonalInfoRequestDto;

@Data
@AllArgsConstructor
public class ChangePersonalInfoMappingDto {
    private UUID clientExternalId;
    private ChangePersonalInfoRequestDto clientInfo;
}
