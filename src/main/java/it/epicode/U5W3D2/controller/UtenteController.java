package it.epicode.U5W3D2.controller;

import it.epicode.U5W3D2.model.Utente;
import it.epicode.U5W3D2.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utenti")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    @GetMapping
    public List<Utente> getAllUtenti() {
        return utenteService.getAllUtente();
    }
}
