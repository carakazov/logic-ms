package notes.project.logic.handler;

import dto.integration.kafka.ServiceClientAdditionalInfoKafkaDto;

public interface NewClientHandler {
    void handle(ServiceClientAdditionalInfoKafkaDto userInfo);
}
