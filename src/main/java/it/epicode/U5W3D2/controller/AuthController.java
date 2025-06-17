package it.epicode.U5W3D2.controller;

import it.epicode.U5W3D2.dto.LoginDto;
import it.epicode.U5W3D2.dto.UtenteDto;
import it.epicode.U5W3D2.exception.ValidationException;
import it.epicode.U5W3D2.model.Utente;
import it.epicode.U5W3D2.service.AuthService;
import it.epicode.U5W3D2.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private UtenteService UtenteService;

    @Autowired
    private AuthService authService;

    @PostMapping("/auth/register")
    public Utente register(@RequestBody @Validated UtenteDto UtenteDto, BindingResult bindingResult) throws ValidationException{
        if(bindingResult.hasErrors()){
            throw new ValidationException(bindingResult.getAllErrors().stream().
                    map(objectError -> objectError.getDefaultMessage()).
                    reduce("", (s,e)->s+e));

        }

        return UtenteService.saveUtente(UtenteDto);
    }


    @PostMapping("/auth/login")
    public String login(@RequestBody @Validated LoginDto loginDto, BindingResult bindingResult) throws ValidationException {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors().stream().
                    map(objectError -> objectError.getDefaultMessage()).
                    reduce("", (s, e) -> s + e));
        }

        return authService.login(loginDto);
    }
}
