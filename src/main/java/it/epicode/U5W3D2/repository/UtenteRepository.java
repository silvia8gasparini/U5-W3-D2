package it.epicode.U5W3D2.repository;

import it.epicode.U5W3D2.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtenteRepository extends JpaRepository<Utente,Integer> {

    public Optional<Utente> findByUsername(String username);

}
