package it.epicode.U5W3D2.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import it.epicode.U5W3D2.exception.NotFoundException;
import it.epicode.U5W3D2.model.Utente;
import it.epicode.U5W3D2.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTool {
    @Value("${jwt.duration}")
    private long durata;

    @Value("${jwt.secret}")
    private String chiaveSegreta;

    @Autowired
    private UtenteService utenteService;

    public String createToken(Utente Utente){

        return Jwts.builder().issuedAt(new Date()).
                expiration(new Date(System.currentTimeMillis()+durata)).
                subject(String.valueOf(Utente.getId())).
                signWith(Keys.hmacShaKeyFor(chiaveSegreta.getBytes())).
                compact();
    }

    public void validateToken(String token){
        Jwts.parser().verifyWith(Keys.hmacShaKeyFor(chiaveSegreta.getBytes())).
                build().parse(token);
    }

    public Utente getUtenteFromToken(String token) throws NotFoundException {
        int id = Integer.parseInt(Jwts.parser().verifyWith(Keys.hmacShaKeyFor(chiaveSegreta.getBytes())).
                build().parseSignedClaims(token).getPayload().getSubject());

        return utenteService.getUtente(id);
    }

}
