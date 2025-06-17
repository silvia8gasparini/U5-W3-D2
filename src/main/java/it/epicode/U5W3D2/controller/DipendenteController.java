package it.epicode.U5W3D2.controller;

import it.epicode.U5W3D2.dto.DipendenteDto;
import it.epicode.U5W3D2.exception.NotFoundException;
import it.epicode.U5W3D2.exception.ValidationException;
import it.epicode.U5W3D2.model.Dipendente;
import it.epicode.U5W3D2.service.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {

    @Autowired
    private DipendenteService dipendenteService;

    @PostMapping
    public Dipendente saveDipendente(@RequestBody @Validated DipendenteDto dipendenteDto, BindingResult bindingResult) throws ValidationException {
        if (bindingResult.hasErrors()){
            throw new ValidationException(bindingResult.getAllErrors()
                    .stream().map(objectError -> objectError.getDefaultMessage())
                    .reduce("", (s1,s2) -> s1 +  " " + s2));
        }
        return dipendenteService.saveDipendente(dipendenteDto);
    }

    @GetMapping("")
    public Page<Dipendente> getAllDipendenti(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10")int size, @RequestParam(defaultValue = "id") String sortby) {
        return dipendenteService.getAllDipendenti(page, size, sortby);
    }

    @GetMapping("/{id}")
    public Dipendente getDipendente(@PathVariable int id) throws NotFoundException {
        return dipendenteService.getDipendente(id);
    }

    @PutMapping("/{id}")
    public Dipendente updateDipendente(@PathVariable int id, @RequestBody @Validated DipendenteDto dto,
                                       BindingResult bindingResult) throws ValidationException, NotFoundException {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors()
                    .stream().map(err -> err.getDefaultMessage())
                    .reduce("", (s1, s2) -> s1 + " " + s2));
        }
        return dipendenteService.updateDipendente(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteDipendente(@PathVariable int id) throws NotFoundException {
        dipendenteService.deleteDipendente(id);
    }

    @PatchMapping("/{id}/avatar")
    public String uploadAvatar(@PathVariable int id, @RequestParam("file") MultipartFile file)
            throws NotFoundException, IOException {
        return dipendenteService.patchDipendente(id, file);
    }
}
