package notes.project.logic.handler;

import dto.integration.kafka.UserInfoDto;

public interface NewClientHandler {
    void handle(UserInfoDto userInfo);
}
