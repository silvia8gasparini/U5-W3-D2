package it.epicode.U5W3D2.service;

import it.epicode.U5W3D2.dto.ViaggioDto;
import it.epicode.U5W3D2.enumeration.StatoViaggio;
import it.epicode.U5W3D2.exception.NotFoundException;
import it.epicode.U5W3D2.model.Viaggio;
import it.epicode.U5W3D2.repository.ViaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViaggioService {

    @Autowired
    private ViaggioRepository viaggioRepository;

    public Viaggio saveViaggio(ViaggioDto viaggioDto) throws NotFoundException{
        Viaggio viaggio = new Viaggio();
        viaggio.setDestinazione(viaggioDto.getDestinazione());
        viaggio.setDataViaggio(viaggioDto.getDataViaggio());
        viaggio.setStatoViaggio(viaggioDto.getStatoViaggio());
        return viaggioRepository.save(viaggio);
    }

    public List<Viaggio> getAllViaggi() {
        return viaggioRepository.findAll();
    }

    public Viaggio getViaggio(int id) {
        return viaggioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Viaggio con ID " + id + " non trovato"));
    }

    public Viaggio updateViaggio(int id, ViaggioDto viaggioDto) throws NotFoundException{
        Viaggio viaggioDaAggiornare = getViaggio(id);
        viaggioDaAggiornare.setDestinazione(viaggioDto.getDestinazione());
        viaggioDaAggiornare.setDataViaggio(viaggioDto.getDataViaggio());
        viaggioDaAggiornare.setStatoViaggio(viaggioDto.getStatoViaggio());
        return viaggioRepository.save(viaggioDaAggiornare);
    }

    public void deleteViaggio(int id) {
        Viaggio viaggioDaCancellare = getViaggio(id);
        viaggioRepository.delete(viaggioDaCancellare);
    }

    public Viaggio cambiaStato(int id, String statoViaggio) {
        StatoViaggio stato = StatoViaggio.valueOf(statoViaggio);
        Viaggio viaggio = getViaggio(id);
        viaggio.setStatoViaggio(stato);
        return viaggioRepository.save(viaggio);
    }

}

