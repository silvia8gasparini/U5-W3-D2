package it.epicode.U5W3D2.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PrenotazioneDto {
    @NotNull(message = "La data è obbligatoria")
    private LocalDate dataRichiesta;
    private String note;
    @NotNull(message = "Id dipendente obbligatorio")
    private int dipendenteId;
    @NotNull(message = "Id viaggio obbligatorio")
    private int viaggioId;
}
