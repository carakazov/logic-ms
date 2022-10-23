package notes.project.logic.service.integration.http.client;

import java.util.UUID;

import notes.project.logic.dto.integration.filesystem.*;
import notes.project.logic.utils.token.TokenRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/file")
    @TokenRequest
    ResponseEntity<FileSystemChangeFileDirectoryResponseDto> changeFileDirectory(@RequestBody FileSystemChangeFileDirectoryRequestDto request);

    @GetMapping("/file/{id}")
    @TokenRequest
    ResponseEntity<FileSystemFileResponseDto> readFile(@PathVariable(name = "id") UUID externalId);

    @PutMapping("/file/{id}")
    @TokenRequest
    ResponseEntity<Void> updateFile(@PathVariable(name = "id") UUID externalId, @RequestBody
        FileSystemUpdateFileRequestDto request);

    @DeleteMapping("/file/{id}")
    @TokenRequest
    ResponseEntity<Void> deleteFile(@PathVariable(name = "id") UUID externalId);

    @GetMapping("/file/{id}/archiveHistory")
    @TokenRequest
    ResponseEntity<FileSystemArchiveResponseDto> getFileArchiveHistory(@PathVariable(name = "id") UUID externalId);

    @GetMapping("/file/{id}/deleteHistory")
    @TokenRequest
    ResponseEntity<FileSystemDeleteHistoryResponseDto> getFileDeleteHistory(@PathVariable(name = "id") UUID externalId);

    @GetMapping("/file/{id}/replacingHistory")
    @TokenRequest
    ResponseEntity<FileSystemReplacingHistoryResponseDto> getFileReplacingHistory(@PathVariable(name = "id") UUID externalId);

    @GetMapping("/file/version/{id}")
    @TokenRequest
    ResponseEntity<FileSystemFileVersionDto> getFileVersion(@PathVariable(name = "id") UUID externalId);
}
