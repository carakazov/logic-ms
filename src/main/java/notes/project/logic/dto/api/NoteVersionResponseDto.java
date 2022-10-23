package notes.project.logic.dto.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(description = "Контент файла определенной версии")
public class NoteVersionResponseDto {
    @ApiModelProperty(value = "Контент")
    private String content;
}
