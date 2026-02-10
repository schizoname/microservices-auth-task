package com.example.auth_api.service;

import com.example.auth_api.*;
import com.example.auth_api.repository.ProcessRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ProcessService {
    private final ProcessRepository processRepository;
    private final RestClient restClient;
    public ProcessService(
            ProcessRepository processRepository,
            RestClient restClient) {
        this.processRepository = processRepository;
        this.restClient = restClient;
    }

    public ProcessResponse process(String text, UUID userId) {
        DataApiResponse response = restClient.post()
                .uri("http://data-api:8081/api/process")
                .header("X-Internal-Token", "my-secret-token")
                .body(new DataApiRequest(text))
                .retrieve()
                .body(DataApiResponse.class);

        if (response == null)
            throw new RuntimeException("data-api returned empty response");

        processRepository.save(new ProcessEntity(userId, text, response.result(), LocalDateTime.now()));
        return new ProcessResponse(text, response.result());
    }
}
