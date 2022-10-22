package notes.project.logic.controller;

import java.util.UUID;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import notes.project.logic.dto.api.*;
import notes.project.logic.service.api.NoteService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Api("Контроллер для управления записками")
@RequestMapping("/note")
public class NoteController {
    private final NoteService noteService;

    @PostMapping
    @ApiOperation(value = "Создагте записки")
    @Secured("ROLE_USER")
    public CreateNoteResponseDto createNote(@RequestBody CreateNoteRequestDto request) {
        return noteService.createNote(request);
    }

    @PutMapping
    @ApiOperation(value = "Перемещение записки")
    @Secured("ROLE_USER")
    public MoveNoteResponseDto moveNote(@RequestBody MoveNoteRequestDto request) {
        return noteService.moveNote(request);
    }

    @GetMapping("/{externalId}")
    @ApiOperation(value = "Чтение записки")
    @Secured("ROLE_USER")
    public NoteResponseDto readNote(@PathVariable(name = "externalId")@ApiParam(name = "Внешний ID записки") UUID externalId)  {
        return noteService.readNote(externalId);
    }
}
