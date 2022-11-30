package notes.project.logic.dto.api;

import java.util.UUID;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import notes.project.logic.model.AccessMode;

@Data
@Accessors(chain = true)
@ApiModel(description = "Описание пользователя с доступом к записке")
public class AccessorDto {
    @ApiModelProperty(value = "Внешний ID пользователя")
    private UUID externalId;
    @ApiModelProperty(value = "Имя")
    private String name;
    @ApiModelProperty(value = "Фамилия")
    private String surname;
    @ApiModelProperty(value = "Отчество")
    private String middleName;
    @ApiModelProperty(value = "Тип доступа")
    private AccessMode accessMode;
}
