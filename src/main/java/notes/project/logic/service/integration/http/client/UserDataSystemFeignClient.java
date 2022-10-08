package notes.project.logic.service.integration.http.client;

import java.util.UUID;

import notes.project.logic.dto.integration.userdatasystem.UserDataSystemPersonalInfoDto;
import notes.project.logic.oauth.TokenSource;
import notes.project.logic.utils.token.TokenRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "${application.externalServices.userData.name}", url = "${application.externalServices.userData.url}")
public interface UserDataSystemFeignClient {
    @GetMapping("/client/{externalId}")
    @TokenRequest(tokenSource = TokenSource.INTERNAL_SERVER)
    ResponseEntity<UserDataSystemPersonalInfoDto> getPersonalInfo(@PathVariable(name = "externalId") UUID externalId);
}
