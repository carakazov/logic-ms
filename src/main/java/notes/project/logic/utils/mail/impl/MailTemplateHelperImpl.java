package notes.project.logic.utils.mail.impl;

import java.util.Objects;

import lombok.RequiredArgsConstructor;
import notes.project.logic.config.ApplicationProperties;
import notes.project.logic.dto.integration.rabbit.FileSystemMessage;
import notes.project.logic.utils.mail.MailTemplateHelper;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class MailTemplateHelperImpl implements MailTemplateHelper {
    private static final String DAYS_COUNT_PLACEHOLDER = "{daysCount}";

    private final ApplicationProperties properties;

    @Override
    public String createMessage(FileSystemMessage message) {
        String template = properties.getMessageTemplates().get(message.getEventCode().getCode());
        return Objects.nonNull(message.getDaysBeforeDelete()) ? template.replace(DAYS_COUNT_PLACEHOLDER, message.getDaysBeforeDelete().toString()) : template;
    }
}
