package notes.project.logic.dto.api;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(description = "Описание кластера")
public class ClusterDto {
    @ApiModelProperty(value = "Название")
    private String title;
    @ApiModelProperty(value = "Внешний ID")
    private UUID externalId;
    @ApiModelProperty(value = "Дата создания")
    private LocalDateTime creationDate;
    @ApiModelProperty(value = "Список директорий")
    private List<DirectoryInfoDto> directories;
}
