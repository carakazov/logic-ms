package notes.project.logic.dto.api;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(description = "Описание перемещения")
public class ReplacingHistoryRecordDto {
    @ApiModelProperty(value = "Дата перемещения")
    private LocalDateTime replacingDate;
    @ApiModelProperty(value = "Исходная директория")
    private DirectoryReplacingHistoryDto sourceDirectory;
    @ApiModelProperty(value = "Конечная директория")
    private DirectoryReplacingHistoryDto targetDirectory;
}
