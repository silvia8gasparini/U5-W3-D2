package it.epicode.U5W3D2.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginDto {
    @NotEmpty(message = "Lo username non può essere vuoto")
    private String username;
    @NotEmpty(message = "La password non può essere vuoto")
    private String password;
}
