package notes.project.logic.dto.api;

import java.util.List;
import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import notes.project.logic.dto.integration.userdatasystem.ChangePersonalInfoMarker;

@Data
@ApiModel(description = "Изменение личных данных")
@Accessors(chain = true)
public class ChangePersonalInfoRequestDto {
    @ApiModelProperty(value = "Новые значения базовых параметров")
    private Map<ChangePersonalInfoMarker, String> newValues;
    @ApiModelProperty(value = "Изменения дополнительных параметров")
    private List<ChangeAdditionalInfoDto> changeAdditionalInfo;
}
