package notes.project.logic.dto.api;

import java.util.List;
import java.util.UUID;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(description = "Доступы к записке")
public class AccessorsListResponseDto {
    @ApiModelProperty(value = "Внешний ID записки")
    private UUID externalId;
    @ApiModelProperty(value = "Список пользователей с доступами")
    private List<AccessorDto> accessors;
}
