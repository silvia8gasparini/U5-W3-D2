package it.epicode.U5W3D2.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UtenteDto {
    @NotEmpty(message = "Il nome non può essere vuoto")
    private String nome;
    @NotEmpty(message = "Il cognome non può essere vuoto")
    private String cognome;
    @NotEmpty(message = "Lo username non può essere vuoto")
    private String username;
    @NotEmpty(message = "La password non può essere vuoto")
    private String password;
}
