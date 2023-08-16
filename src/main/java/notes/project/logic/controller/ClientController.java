package notes.project.logic.controller;

import java.util.UUID;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import notes.project.logic.dto.api.*;
import notes.project.logic.dto.api.admin.ClusterAdminDto;
import notes.project.logic.dto.api.admin.DeletedObjectsList;
import notes.project.logic.utils.mapper.dto.ChangePersonalInfoMappingDto;
import notes.project.logic.service.api.ClientService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Api(value = "Контроллер по управлению клиентами")
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;

    @GetMapping("/{externalId}")
    @ApiOperation("Запрос личных данных пользователя")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
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

    @PutMapping
    @ApiOperation("Обновление личных данных")
    @Secured("ROLE_USER")
    public PersonalInfoDto changePersonalInfo(
        @RequestBody ChangePersonalInfoRequestDto request,
        @RequestParam(name = "createNew", defaultValue = "false") Boolean createNew) {
        return clientService.changePersonalInfo(request, createNew);
    }

    @GetMapping("/list")
    @ApiOperation("Получение всех клиентов системы")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public AllClientsResponseDto getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{externalId}/admin")
    @ApiOperation("Запрос кластера для админа")
    @Secured({"ROLE_ADMIN"})
    public ClusterAdminDto getClusterForAdmin(@PathVariable(name = "externalId") UUID externalId) {
        return clientService.getClientInfo(externalId);
    }

    @GetMapping("/{externalId}/deleted")
    @ApiOperation("Удаленные объекты клиента")
    @Secured({"ROLE_ADMIN"})
    public DeletedObjectsList getDeletedObjects(@PathVariable(name = "externalId") UUID externalId) {
        return clientService.getDeletedObjects(externalId);
    }
}
