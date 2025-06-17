package it.epicode.U5W3D2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Prenotazione {
    @Id
    @GeneratedValue
    private int id;
    private LocalDate dataRichiesta;
    private String note;


    @ManyToOne
    private Dipendente dipendente;

    @ManyToOne
    private Viaggio viaggio;
}
