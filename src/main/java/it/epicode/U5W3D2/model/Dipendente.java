package it.epicode.U5W3D2.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Dipendente {
    @Id
    @GeneratedValue
    private int id;
    private String Utentename;
    private String nome;
    private String cognome;
    private String email;
    private String avatar;
}
