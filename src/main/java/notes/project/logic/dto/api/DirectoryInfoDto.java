package notes.project.logic.dto.api;

import java.time.LocalDateTime;
import java.util.UUID;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(description = "Краткое описание директории")
public class DirectoryInfoDto {
    @ApiModelProperty(value = "Название")
    private String title;
    @ApiModelProperty(value = "Внешний ID")
    private UUID externalId;
    @ApiModelProperty(value = "Дата создания")
    private LocalDateTime creationDate;
}
