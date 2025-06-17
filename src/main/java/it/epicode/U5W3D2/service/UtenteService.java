package it.epicode.U5W3D2.service;

import it.epicode.U5W3D2.dto.UtenteDto;
import it.epicode.U5W3D2.enumeration.Role;
import it.epicode.U5W3D2.exception.NotFoundException;
import it.epicode.U5W3D2.model.Utente;
import it.epicode.U5W3D2.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Utente saveUtente(UtenteDto utenteDto){
        Utente utente = new Utente();
        utente.setNome(utenteDto.getNome());
        utente.setCognome(utenteDto.getCognome());
        utente.setUsername(utenteDto.getUsername());
        utente.setPassword(passwordEncoder.encode(utenteDto.getPassword()));
        utente.setRole(Role.USER);

        return utenteRepository.save(utente);
    }

    public List<Utente> getAllUtente(){

        return utenteRepository.findAll();
    }

    public Utente getUtente(int id) throws NotFoundException {
        return utenteRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Utente con id " + id + " non trovato"));
    }

    public Utente updateUtente(int id, UtenteDto utenteDto) throws NotFoundException {
        Utente utenteDaAggiornare = getUtente(id);

        utenteDaAggiornare.setNome(utenteDto.getNome());
        utenteDaAggiornare.setCognome(utenteDto.getCognome());
        utenteDaAggiornare.setUsername(utenteDto.getUsername());

        if (!passwordEncoder.matches(utenteDto.getPassword(), utenteDaAggiornare.getPassword())) {
            utenteDaAggiornare.setPassword(passwordEncoder.encode(utenteDto.getPassword()));
        }

        return utenteRepository.save(utenteDaAggiornare);
    }

    public void deleteUtente(int id) throws NotFoundException {
        Utente utenteDaCancellare = getUtente(id);

        utenteRepository.delete(utenteDaCancellare);
    }
}
