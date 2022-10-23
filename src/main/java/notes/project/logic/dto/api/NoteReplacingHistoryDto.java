package notes.project.logic.dto.api;

import java.util.UUID;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(description = "Краткое описание записки")
public class NoteReplacingHistoryDto {
    @ApiModelProperty(value = "Название записки")
    private String noteTitle;
    @ApiModelProperty(value = "Внешний ID записки")
    private UUID noteExternalId;
}
