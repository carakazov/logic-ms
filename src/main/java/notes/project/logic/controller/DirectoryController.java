package notes.project.logic.controller;

import java.util.UUID;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import notes.project.logic.dto.api.CreateDirectoryRequestDto;
import notes.project.logic.dto.api.CreateDirectoryResponseDto;
import notes.project.logic.dto.api.DirectoryInfoDto;
import notes.project.logic.service.api.DirectoryService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/directory")
@Api("Контроллер по управлению ")
public class DirectoryController {
    private final DirectoryService directoryService;

    @PostMapping
    @ApiOperation("Создание директории")
    @Secured("ROLE_USER")
    public CreateDirectoryResponseDto createDirectory(@RequestBody CreateDirectoryRequestDto request) {
        return directoryService.createDirectory(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Удаление директории")
    @Secured("ROLE_USER")
    public void deleteDirectory(@PathVariable(name = "id") @ApiParam(value = "Внешний ID удаляемой директории") UUID externalId) {
        directoryService.deleteDirectory(externalId);
    }

    @GetMapping("/{id}")
    @ApiOperation("Чтение директории")
    @Secured("ROLE_USER")
    public DirectoryInfoDto readDirectory(@PathVariable(name = "id") @ApiParam(value = "Внешний ID удаляемой директории") UUID externalId) {
        return directoryService.readDirectory(externalId);
    }
}
