package notes.project.logic.service.integration.http.client;

import notes.project.logic.dto.integration.filesystem.CreateClusterRequestDto;
import notes.project.logic.dto.integration.filesystem.CreateClusterResponseDto;
import notes.project.logic.oauth.TokenSource;
import notes.project.logic.utils.token.TokenRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "${application.externalServices.fileSystem.name}", url = "${application.externalServices.fileSystem.url}")
public interface FileSystemFeignClient {
    @PostMapping("/cluster")
    @TokenRequest
    ResponseEntity<CreateClusterResponseDto> createCluster(@RequestBody CreateClusterRequestDto request);
}
