package notes.project.logic.utils.mail;

import notes.project.logic.dto.integration.rabbit.FileSystemMessage;

public interface MailSender {
    void sendEmail(FileSystemMessage message, String email);
}
