package notes.project.logic.dto.api;

import java.util.UUID;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(description = "Небольшое описание директории")
public class DirectoryReplacingHistoryDto {
    @ApiModelProperty(value = "Название директории")
    private String directoryTitle;
    @ApiModelProperty(value = "Внешний ID директории")
    private UUID directoryExternalId;
}
