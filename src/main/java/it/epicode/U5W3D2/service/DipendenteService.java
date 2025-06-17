package it.epicode.U5W3D2.service;

import com.cloudinary.Cloudinary;
import it.epicode.U5W3D2.dto.DipendenteDto;
import it.epicode.U5W3D2.exception.NotFoundException;
import it.epicode.U5W3D2.model.Dipendente;
import it.epicode.U5W3D2.repository.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;

@Service
public class DipendenteService {

    @Autowired
    private DipendenteRepository dipendenteRepository;

    @Autowired
    private Cloudinary cloudinary;

    public Dipendente saveDipendente(DipendenteDto dipendenteDto) throws NotFoundException {
        Dipendente dipendente = new Dipendente();
        dipendente.setUtentename(dipendenteDto.getUtentename());
        dipendente.setNome(dipendenteDto.getNome());
        dipendente.setCognome(dipendenteDto.getCognome());
        dipendente.setEmail(dipendenteDto.getEmail());
        dipendente.setAvatar(dipendenteDto.getAvatar());
        return dipendenteRepository.save(dipendente);
    }

    public Page<Dipendente> getAllDipendenti(int page, int size, String sortBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return dipendenteRepository.findAll(pageable);
    }

    public Dipendente getDipendente(int id) throws NotFoundException {
        return dipendenteRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("Dipendente con id " + id + " non trovato"));
    }

    public Dipendente updateDipendente(int id, DipendenteDto dipendenteDto) throws NotFoundException {
        Dipendente dipendenteDaAggiornare = getDipendente(id);
        dipendenteDaAggiornare.setUtentename(dipendenteDto.getUtentename());
        dipendenteDaAggiornare.setNome(dipendenteDto.getNome());
        dipendenteDaAggiornare.setCognome(dipendenteDto.getCognome());
        dipendenteDaAggiornare.setEmail(dipendenteDto.getEmail());
        dipendenteDaAggiornare.setAvatar(dipendenteDto.getAvatar());
        return dipendenteRepository.save(dipendenteDaAggiornare);
    }

    public void deleteDipendente(int id) throws NotFoundException {
        Dipendente dipendenteDaCancellare = getDipendente(id);
        dipendenteRepository.delete(dipendenteDaCancellare);
    }

    public String patchDipendente(int id, MultipartFile file) throws NotFoundException, IOException {
        Dipendente dipendenteDaPatchare = getDipendente(id);
        String url= (String)cloudinary.uploader().upload(file.getBytes(), Collections.emptyMap()).get("url");
        dipendenteDaPatchare.setAvatar(url);
        dipendenteRepository.save(dipendenteDaPatchare);
        return url;
    }

}
