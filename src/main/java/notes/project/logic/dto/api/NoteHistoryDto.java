package notes.project.logic.dto.api;

import java.util.UUID;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(description = "Краткая инфомрация о записке")
public class NoteHistoryDto {
    @ApiModelProperty(value = "Внешний ID записки")
    private UUID noteExternalId;
    @ApiModelProperty(value = "Название записки")
    private String noteTitle;
}
