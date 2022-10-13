package notes.project.logic.dto.api;

import java.time.LocalDateTime;
import java.util.UUID;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(description = "Ответ на создание кластера")
public class CreateDirectoryResponseDto {
    @ApiModelProperty(value = "Название кластера")
    private String clusterName;
    @ApiModelProperty(value = "Дата и время создания директории")
    private LocalDateTime creationDate;
    @ApiModelProperty(value = "Название директории")
    private String directoryName;
    @ApiModelProperty(value = "Внешний ID директории")
    private UUID externalId;
}
