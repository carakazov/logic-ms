package notes.project.logic.dto.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@ApiModel(description = "Изменения дополнительных параметров")
@Accessors(chain = true)
public class ChangeAdditionalInfoDto {
    @ApiModelProperty(value = "Название параметра")
    private String type;
    @ApiModelProperty(value = "Новое значение")
    private String newValue;
}
