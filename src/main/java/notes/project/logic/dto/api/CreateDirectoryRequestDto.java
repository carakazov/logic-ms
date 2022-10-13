package notes.project.logic.dto.api;

import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(description = "Запрос на создание директории")
public class CreateDirectoryRequestDto {
    @ApiModelProperty(value = "Название директории", required = true)
    @NotNull
    private String directoryName;

    @ApiModelProperty(value = "Внешний ID кластера", required = true)
    @NotNull
    private UUID clusterExternalId;
}
