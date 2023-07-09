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
    @Size(min = 1, max = 125)
    @ApiModelProperty(value = "Название директории", required = true)
    private String directoryName;
}
