package notes.project.logic.dto.api;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(description = "Описание клиента")
public class PersonalInfoDto {
    @ApiModelProperty(value = "Имя")
    private String name;
    @ApiModelProperty(value = "Фамилия")
    private String surname;
    @ApiModelProperty(value = "Отчество")
    private String middleName;
    @ApiModelProperty(value = "Дата рождения")
    private LocalDate birthDate;
    @ApiModelProperty(value = "Внешний ID пользователя")
    private UUID externalId;
    @ApiModelProperty(value = "Электронная почта")
    private String email;
    @ApiModelProperty(value = "Дополнительная информация по пользователю")
    private List<AdditionalInfoDto> additionalInfo;
}
