package notes.project.logic.utils.mail.impl;

import java.util.concurrent.ExecutorService;

import lombok.RequiredArgsConstructor;
import notes.project.logic.dto.integration.rabbit.FileSystemMessage;
import notes.project.logic.utils.mail.MailSender;
import notes.project.logic.utils.mail.MailTemplateHelper;
import notes.project.logic.utils.mail.SimpleMessageMapper;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailSenderImpl implements MailSender {
    private final MailTemplateHelper mailTemplateHelper;
    private final JavaMailSender javaMailSender;
    private final ExecutorService executorService;
    private final SimpleMessageMapper simpleMessageMapper;

    @Override
    public void sendEmail(FileSystemMessage message, String email) {
        String messageText = mailTemplateHelper.createMessage(message);
        SimpleMailMessage mail = simpleMessageMapper.to(messageText, email);
        executorService.execute(() -> javaMailSender.send(mail));
    }
}
