package notes.project.logic.service.integration.broker.impl;

import java.util.function.Consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import notes.project.logic.dto.integration.rabbit.FileSystemMessage;
import notes.project.logic.handler.FileSystemMessageHandler;
import notes.project.logic.service.integration.broker.FileSystemEventListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileSystemEventListenerImpl implements FileSystemEventListener {
    private final ObjectMapper objectMapper;
    private final FileSystemMessageHandler fileSystemMessageHandler;

    @Override
    public void listen(String message) {
        FileSystemMessage fileSystemMessage;
        try {
            fileSystemMessage = objectMapper.readValue(message, FileSystemMessage.class);
        } catch(Exception e) {
            log.error("Can not read message {}", message, e);
            return;
        }
        fileSystemMessageHandler.handle(fileSystemMessage);
    }

    @Bean
    public Consumer<String> fileSystemNotificationListener() {
        return this::listen;
    }
}