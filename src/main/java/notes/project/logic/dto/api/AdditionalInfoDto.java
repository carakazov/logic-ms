package notes.project.logic.dto.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(value = "Дополнительная информация по пользователю")
public class AdditionalInfoDto {
    @ApiModelProperty(value = "Название поля")
    private String type;
    @ApiModelProperty(value = "Значение")
    private String value;
}
