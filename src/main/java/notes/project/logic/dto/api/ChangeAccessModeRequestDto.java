package notes.project.logic.dto.api;

import java.util.UUID;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import notes.project.logic.model.AccessMode;

@Data
@Accessors(chain = true)
@ApiModel(description = "Запрос на изменение типа доступа к записке")
public class ChangeAccessModeRequestDto {
    @ApiModelProperty(value = "Внешний ID записка")
    private UUID noteExternalId;
    @ApiModelProperty(value = "Внешний ID клиента")
    private UUID clientExternalId;
    @ApiModelProperty(value = "Типа доступа")
    private AccessMode accessMode;
}
