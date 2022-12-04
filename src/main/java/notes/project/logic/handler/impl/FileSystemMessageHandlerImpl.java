package notes.project.logic.handler.impl;

import java.util.Objects;
import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import notes.project.logic.dto.api.PersonalInfoDto;
import notes.project.logic.dto.integration.rabbit.FileSystemMessage;
import notes.project.logic.handler.FileSystemMessageHandler;
import notes.project.logic.service.api.ClientService;
import notes.project.logic.utils.mail.MailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FileSystemMessageHandlerImpl implements FileSystemMessageHandler {
    private final MailSender mailSender;
    private final ClientService clientService;

    @Override
    @Transactional
    public void handle(FileSystemMessage message) {
        PersonalInfoDto personalInfo = clientService.getPersonalInfoByClusterExternalId(message.getClusterExternalId());
        mailSender.sendEmail(message, personalInfo.getEmail());
        if(Objects.nonNull(message.getDaysBeforeDelete())) {
            clientService.renewClientCluster(message.getClusterExternalId());
        }
    }
}
