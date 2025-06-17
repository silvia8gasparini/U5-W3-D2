package it.epicode.U5W3D2.service;

import it.epicode.U5W3D2.dto.LoginDto;
import it.epicode.U5W3D2.exception.NotFoundException;
import it.epicode.U5W3D2.exception.UnAuthorizedException;
import it.epicode.U5W3D2.model.Utente;
import it.epicode.U5W3D2.repository.UtenteRepository;
import it.epicode.U5W3D2.security.JwtTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private JwtTool jwtTool;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String login(LoginDto loginDto) throws NotFoundException {
        Utente utente = utenteRepository.findByUsername(loginDto.getUsername()).
                orElseThrow(() -> new NotFoundException("Utente con questo Utentename/password non trovato"));

        if (!passwordEncoder.matches(loginDto.getPassword(), utente.getPassword())) {
            throw new UnAuthorizedException("Password non corretta");
        }
        else{
           return jwtTool.createToken(utente);
        }
    }
}
