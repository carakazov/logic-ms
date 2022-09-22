package notes.project.logic.service.integration.broker.impl;

import java.io.StringReader;
import java.util.function.Consumer;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import dto.integration.kafka.UserInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import notes.project.logic.exception.ExceptionCode;
import notes.project.logic.exception.LogicMsException;
import notes.project.logic.handler.NewClientHandler;
import notes.project.logic.service.integration.broker.NewClientListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NewClientListenerImpl implements NewClientListener {
    private final Unmarshaller unmarshaller;
    private final NewClientHandler handler;

    @Override
    public void listen(String message) {
        try {
            log.info("Accepted message {}", message);
            StringReader stringReader = new StringReader(message);
            UserInfoDto userInfo = (UserInfoDto) unmarshaller.unmarshal(stringReader);
            handler.handle(userInfo);
        } catch(Exception exception) {
            log.error("Exception occurred {}", exception.getMessage());
            throw new LogicMsException(ExceptionCode.INTEGRATION_ERROR, exception.getMessage());
        }
    }

    @Bean
    public Consumer<String> newUserListener() {
        return this::listen;
    }
}
