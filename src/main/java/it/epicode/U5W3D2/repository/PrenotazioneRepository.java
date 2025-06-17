package it.epicode.U5W3D2.repository;

import it.epicode.U5W3D2.model.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione,Integer> {
    boolean existsByDipendente_IdAndDataRichiesta(int dipendenteId, LocalDate dataRichiesta);

}
