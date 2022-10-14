package notes.project.logic.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import notes.project.logic.dto.api.CreateDirectoryRequestDto;
import notes.project.logic.dto.api.CreateDirectoryResponseDto;
import notes.project.logic.service.api.DirectoryService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}