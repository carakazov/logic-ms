package notes.project.logic.controller;

import java.util.Map;
import java.util.UUID;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import notes.project.logic.dto.api.ClusterDto;
import notes.project.logic.dto.api.DeleteHistoryResponseDto;
import notes.project.logic.dto.api.PersonalInfoDto;
import notes.project.logic.service.api.ClientService;
import notes.project.logic.utils.AuthHelper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Api(value = "Контроллер по управлению клиентами")
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;

    @GetMapping("/{externalId}")
    @ApiOperation("Запрос личных данных пользователя")
    @Secured("ROLE_USER")
    public PersonalInfoDto getPersonalInfo(@ApiParam(value = "Внешний ID клиента") @PathVariable(name = "externalId") UUID externalId) {
        return clientService.getPersonalInfo(externalId);
    }

    @GetMapping
    @ApiOperation("Чтение всего кластера авторизованного клиента")
    @Secured("ROLE_USER")
    public ClusterDto readCluster() {
        return clientService.readCluster();
    }

    @DeleteMapping
    @ApiOperation("Удаление всех своих записок на сервисе")
    @Secured("ROLE_USER")
    public void deleteCluster() {
        clientService.deleteCluster();
    }

    @GetMapping("/{id}/deleteHistory")
    @ApiOperation("Запрос истории удалений и восстановлений всего контента клиента")
    @Secured("ROLE_ADMIN")
    public DeleteHistoryResponseDto getClusterDeleteHistory(@PathVariable(name = "id") @ApiParam(value = "Внешний ID клиента") UUID clientExternalId) {
        return clientService.getClusterDeleteHistory(clientExternalId);
    }
}
