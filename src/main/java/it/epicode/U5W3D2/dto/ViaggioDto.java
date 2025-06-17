package it.epicode.U5W3D2.dto;

import it.epicode.U5W3D2.enumeration.StatoViaggio;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ViaggioDto {
    @NotEmpty(message = "Destinazione obbligatoria")
    private String destinazione;
    @NotNull(message = "Data viaggio obbligatoria")
    private LocalDate dataViaggio;
    private StatoViaggio statoViaggio;
}
