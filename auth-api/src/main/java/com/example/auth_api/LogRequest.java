package com.example.auth_api;

import lombok.Data;

@Data
public class LogRequest{
    private String email;
    private String password;
}
