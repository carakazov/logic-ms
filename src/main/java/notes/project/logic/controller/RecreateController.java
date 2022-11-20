package notes.project.logic.controller;

import java.util.UUID;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import notes.project.logic.service.api.RecreateService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("Контроллер для восстановления объектов")
@RequiredArgsConstructor
@RequestMapping("/recreate")
public class RecreateController {
    private final RecreateService recreateService;

    @PutMapping("/cluster/{id}")
    @ApiOperation("Восстановление всех объектов пользователя")
    @Secured("ROLE_ADMIN")
    public void recreateCluster(@PathVariable(name = "id") @ApiParam(name = "Внешний ID пользователя") UUID externalId) {
        recreateService.recreateCluster(externalId);
    }


    @PutMapping("/directory/{id}")
    @ApiOperation("Восстановление всех объектов пользователя")
    @Secured("ROLE_ADMIN")
    public void recreateDirectory(@PathVariable(name = "id") @ApiParam(name = "Внешний ID директории") UUID externalId) {
        recreateService.recreateDirectory(externalId);
    }


    @PutMapping("/note/{id}")
    @ApiOperation("Восстановление всех объектов пользователя")
    @Secured("ROLE_ADMIN")
    public void recreateNote(@PathVariable(name = "id") @ApiParam(name = "Внешний ID записки") UUID externalId) {
        recreateService.recreateNote(externalId);
    }

}
