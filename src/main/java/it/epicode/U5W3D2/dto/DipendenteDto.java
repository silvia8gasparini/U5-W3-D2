package it.epicode.U5W3D2.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class DipendenteDto {
    @NotEmpty(message = "Utentename obbligatorio")
    private String Utentename;
    @NotEmpty(message = "Nome obbligatorio")
    private String nome;
    @NotEmpty(message = "Cognome obbligatorio")
    private String cognome;
    @NotEmpty(message = "Email obbligatoria")
    private String email;
    private String avatar;
}
