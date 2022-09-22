package notes.project.logic.service.integration.http;

import feign.FeignException;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import notes.project.logic.exception.ExceptionCode;
import notes.project.logic.exception.LogicMsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Slf4j
public abstract class AbstractRestService {
    protected void checkResponse(ResponseEntity<?> responseEntity) {
        if(!HttpStatus.OK.equals(responseEntity.getStatusCode())) {
            log.error("Response body: {}", responseEntity.getBody());
            throw new LogicMsException(ExceptionCode.INTEGRATION_ERROR, "Status code not success");
        }
    }

    protected LogicMsException handleFeignException(FeignException exception) {
        log.error("Error http code: {}", exception.status());
        return new LogicMsException(ExceptionCode.INTEGRATION_ERROR, exception.getMessage());
    }
}
