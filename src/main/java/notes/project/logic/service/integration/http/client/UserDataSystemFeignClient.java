package notes.project.logic.service.integration.http.client;

import java.util.List;
import java.util.UUID;

import io.swagger.annotations.ApiParam;
import notes.project.logic.dto.integration.userdatasystem.UserDataSystemAllClientsResponseDto;
import notes.project.logic.dto.integration.userdatasystem.UserDataSystemChangePersonalInfoRequestDto;
import notes.project.logic.dto.integration.userdatasystem.UserDataSystemPersonalInfoDto;
import notes.project.logic.oauth.TokenSource;
import notes.project.logic.utils.token.TokenRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "${application.externalServices.userData.name}", url = "${application.externalServices.userData.url}")
public interface UserDataSystemFeignClient {
    @GetMapping("/client/{externalId}")
    @TokenRequest(tokenSource = TokenSource.INTERNAL_SERVER)
    ResponseEntity<UserDataSystemPersonalInfoDto> getPersonalInfo(@PathVariable(name = "externalId") UUID externalId);

    @PutMapping("/client")
    @TokenRequest(tokenSource = TokenSource.INTERNAL_SERVER)
    ResponseEntity<UserDataSystemPersonalInfoDto> changePersonalInfo(
        @RequestBody UserDataSystemChangePersonalInfoRequestDto request,
        @RequestParam(name = "createNew") Boolean createNew
        );

    @GetMapping("/client/{systemName}/list")
    @TokenRequest(tokenSource = TokenSource.INTERNAL_SERVER)
    ResponseEntity<UserDataSystemAllClientsResponseDto> getAllClientsOfSystem(@PathVariable(name = "systemName") String systemName);
}
