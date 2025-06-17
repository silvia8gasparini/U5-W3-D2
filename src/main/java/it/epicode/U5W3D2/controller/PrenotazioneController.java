package it.epicode.U5W3D2.controller;

import it.epicode.U5W3D2.dto.PrenotazioneDto;
import it.epicode.U5W3D2.exception.ValidationException;
import it.epicode.U5W3D2.model.Prenotazione;
import it.epicode.U5W3D2.service.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Prenotazione save(@RequestBody @Validated PrenotazioneDto prenotazioneDto, BindingResult bindingResult)
            throws ValidationException {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors().stream().map(err -> err.getDefaultMessage())
                    .reduce("", (s1, s2) -> s1 + " " + s2));
        }
        return prenotazioneService.savePrenotazione(prenotazioneDto);
    }

    @GetMapping("")
    public List<Prenotazione> getAllPrenotazioni() {
        return prenotazioneService.getAllPrenotazioni();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        prenotazioneService.deletePrenotazione(id);
    }
}
