package notes.project.logic.controller;

import java.util.Map;
import java.util.UUID;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import notes.project.logic.dto.api.PersonalInfoDto;
import notes.project.logic.service.api.ClientService;
import notes.project.logic.utils.AuthHelper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api(value = "Контроллер по управлению клиентами")
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;
    private final AuthHelper authHelper;

    @GetMapping("/{externalId}")
    @ApiOperation("Запрос личных данных пользователя")
    @Secured("ROLE_USER")
    public PersonalInfoDto getPersonalInfo(@ApiParam(value = "Внешний ID клиента") @PathVariable(name = "externalId") UUID externalId) {
        return clientService.getPersonalInfo(externalId);
    }
}
