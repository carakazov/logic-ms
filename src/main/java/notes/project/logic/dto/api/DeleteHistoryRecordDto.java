package notes.project.logic.dto.api;

import java.time.LocalDateTime;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(description = "")
public class DeleteHistoryRecordDto {
    @ApiModelProperty(value = "Событие")
    private String event;
    @ApiModelProperty(value = "Дата")
    private LocalDateTime eventDate;
}
