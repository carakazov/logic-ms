package notes.project.logic.validation.dto;

import java.util.UUID;

import liquibase.pro.packaged.A;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class OwningValidationDto {
    private UUID clientExternalIdFromContext;
    private UUID objectOwnerExternalId;
}
