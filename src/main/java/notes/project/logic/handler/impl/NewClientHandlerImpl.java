package notes.project.logic.handler.impl;

import dto.integration.kafka.UserInfoDto;
import lombok.RequiredArgsConstructor;
import notes.project.logic.handler.NewClientHandler;
import notes.project.logic.service.api.ClientService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NewClientHandlerImpl implements NewClientHandler {
    private final ClientService clientService;

    @Override
    public void handle(UserInfoDto userInfo) {
        clientService.save(userInfo.getExternalId());
    }
}
