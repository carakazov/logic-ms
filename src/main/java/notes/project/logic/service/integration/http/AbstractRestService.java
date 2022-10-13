package notes.project.logic.service.integration.http;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import notes.project.logic.exception.ExceptionCode;
import notes.project.logic.exception.IntegrationException;
import notes.project.logic.exception.LogicMsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Slf4j
public abstract class AbstractRestService {

    @Inject
    protected ObjectMapper objectMapper;

    protected void checkResponse(ResponseEntity<?> responseEntity) {
        if(!HttpStatus.OK.equals(responseEntity.getStatusCode())) {
            log.error("Response body: {}", responseEntity.getBody());
            throw new LogicMsException(ExceptionCode.INTEGRATION_ERROR, "Status code not success");
        }
    }

    protected IntegrationException handleFeignException(FeignException exception)  {
        log.error("Error http code: {}", exception.status());
        Map<?, ?> exceptionBody = new HashMap<>();
        if(exception.responseBody().isPresent()) {
            try {
                exceptionBody = objectMapper.readValue(exception.responseBody().get().array(), Map.class);
            } catch(IOException ioException) {
                log.error("Parsing error: {}", ioException.getMessage());
            }
        }
        return new IntegrationException(exception.status(), exceptionBody);
    }
}
