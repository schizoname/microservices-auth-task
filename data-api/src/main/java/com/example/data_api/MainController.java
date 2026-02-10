package com.example.data_api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
public class MainController {
    @Value("${interlal.token}")
    private String internalToken;

    @PostMapping("/process")
    public DataApiResponse process(
            @RequestBody DataApiRequest request,
            @RequestHeader("X-Internal-Token") String token) {
        if (!internalToken.equals(token))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid internal token");

        String result = new StringBuilder(request.input()).reverse().toString().toUpperCase();
        return new DataApiResponse(result);
    }
}
