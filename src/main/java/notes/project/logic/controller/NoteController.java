package notes.project.logic.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import notes.project.logic.dto.api.CreateNoteRequestDto;
import notes.project.logic.dto.api.CreateNoteResponseDto;
import notes.project.logic.service.api.NoteService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
