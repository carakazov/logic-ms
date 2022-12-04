package notes.project.logic.utils.mail;

import notes.project.logic.dto.integration.rabbit.FileSystemMessage;

public interface MailTemplateHelper {
    String createMessage(FileSystemMessage message);
}
