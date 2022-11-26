package notes.project.logic.controller;

import java.util.UUID;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import notes.project.logic.dto.api.*;
import notes.project.logic.service.api.NoteService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
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

    @PutMapping("/{externalId}")
    @ApiOperation(value = "Обновление записки")
    @Secured("ROLE_USER")
    public void updateNote(
        @PathVariable(name = "externalId") @ApiParam(name = "Внешний ID записки") UUID externalId,
        @RequestBody UpdateNoteRequestDto request)
    {
        noteService.updateNote(externalId, request);
    }

    @DeleteMapping("/{externalId}")
    @ApiOperation(value = "Удаление записки")
    @Secured("ROLE_USER")
    public void deleteNote(@PathVariable(name = "externalId") @ApiParam(name = "Внешний ID записки") UUID externalId) {
        noteService.deleteNote(externalId);
    }

    @GetMapping("/{externalId}/archiveHistory")
    @ApiOperation(value = "Получение истории обновления записок")
    @Secured("ROLE_ADMIN")
    public NoteHistoryResponseDto getNoteArchiveHistory(@PathVariable(name = "externalId") @ApiParam(name = "Внешний ID записки") UUID exteranlId) {
        return noteService.getNoteArchiveHistory(exteranlId);
    }

    @GetMapping("/{externalId}/deleteHistory")
    @ApiOperation(value = "Получение истории удалений записки")
    @Secured("ROLE_ADMIN")
    public DeleteHistoryResponseDto getNoteDeleteHistory(@PathVariable(name = "externalId") @ApiParam(name = "Внешний ID записки") UUID externalId) {
        return noteService.getNoteDeleteHistory(externalId);
    }

    @GetMapping("/{externalId}/replacingHistory")
    @ApiOperation(value = "Получение истории перемещения файла")
    @Secured("ROLE_ADMIN")
    public NoteReplacingHistoryResponseDto getNoteReplacingHistory(@PathVariable(name = "externalId") @ApiParam(name = "Внешний ID записки") UUID externalId) {
        return noteService.getNoteReplacingHistory(externalId);
    }

    @GetMapping("/{externalId}/version")
    @ApiOperation(value = "Запрос версии записки")
    @Secured("ROLE_ADMIN")
    public NoteVersionResponseDto getNoteVersion(@PathVariable(name = "externalId") @ApiParam(name = "Внешний ID версии") UUID externalId) {
        return noteService.getNoteVersion(externalId);
    }

    @PostMapping("/changeAccess")
    @ApiOperation(value = "Смена доступа к записке")
    @Secured("ROLE_USER")
    public void changeNoteAccess(@RequestBody ChangeAccessModeRequestDto request) {
        noteService.changeAccess(request);
    }
}
