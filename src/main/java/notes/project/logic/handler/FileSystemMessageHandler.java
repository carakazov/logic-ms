package notes.project.logic.handler;

import notes.project.logic.dto.integration.rabbit.FileSystemMessage;

public interface FileSystemMessageHandler {
    void handle(FileSystemMessage message);
}
