package it.epicode.U5W3D2.controller;

import it.epicode.U5W3D2.dto.ViaggioDto;
import it.epicode.U5W3D2.exception.ValidationException;
import it.epicode.U5W3D2.model.Viaggio;
import it.epicode.U5W3D2.service.ViaggioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/viaggi")
public class ViaggioController {

    @Autowired
    private ViaggioService viaggioService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Viaggio save(@RequestBody @Validated ViaggioDto viaggioDto, BindingResult bindingResult) throws ValidationException {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors().stream().map(err -> err.getDefaultMessage())
                    .reduce("", (s1, s2) -> s1 + " " + s2));
        }
        return viaggioService.saveViaggio(viaggioDto);
    }

    @GetMapping("")
    public List<Viaggio> getAllViaggi() {
        return viaggioService.getAllViaggi();
    }

    @GetMapping("/{id}")
    public Viaggio getById(@PathVariable int id) {
        return viaggioService.getViaggio(id);
    }

    @PutMapping("/{id}")
    public Viaggio update(@PathVariable int id, @RequestBody @Validated ViaggioDto viaggioDto, BindingResult bindingResult)
            throws ValidationException {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors().stream().map(err -> err.getDefaultMessage())
                    .reduce("", (s1, s2) -> s1 + " " + s2));
        }
        return viaggioService.updateViaggio(id, viaggioDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        viaggioService.deleteViaggio(id);
    }

    @PatchMapping("/{id}/stato")
    public Viaggio cambiaStato(@PathVariable int id, @RequestBody Map<String, String> body) {
        String stato = body.get("statoViaggio");
        return viaggioService.cambiaStato(id, stato);
    }
}
