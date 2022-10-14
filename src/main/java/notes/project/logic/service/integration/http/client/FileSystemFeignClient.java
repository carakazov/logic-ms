package notes.project.logic.service.integration.http.client;

import notes.project.logic.dto.integration.filesystem.*;
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

    @PostMapping("/directory")
    @TokenRequest
    ResponseEntity<FileSystemCreateDirectoryResponseDto> createDirectory(@RequestBody FileSystemCreateDirectoryRequestDto request);

    @PostMapping("/file")
    @TokenRequest
    ResponseEntity<FileSystemCreateFileResponseDto> createFile(@RequestBody FileSystemCreateFileRequestDto request);
}
