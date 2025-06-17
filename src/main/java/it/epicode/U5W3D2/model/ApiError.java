package it.epicode.U5W3D2.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiError {
    private String messaggio;
    private LocalDateTime dataErrore;
}
