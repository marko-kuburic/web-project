package com.INprojekat.WEB.service;

import com.INprojekat.WEB.dto.*;
import com.INprojekat.WEB.entity.*;
import com.INprojekat.WEB.repository.KnjigaRepository;
import com.INprojekat.WEB.repository.KorisnikRepository;
import com.INprojekat.WEB.repository.RecenzijaRepository;
import com.INprojekat.WEB.repository.StavkaPoliceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecenzijaService {

    @Autowired
    private RecenzijaRepository recenzijaRepository;
    @Autowired
    private KorisnikRepository korisnikRepository;
    @Autowired
    private StavkaPoliceService stavkaPoliceService;
    @Autowired
    private KnjigaService knjigaService;
    @Autowired
    private StavkaPoliceRepository stavkaPoliceRepository;

    @Autowired
    private PolicaService policaService;

    @Autowired
    private RecenzijaService recenzijaService;
    @Autowired
    private KnjigaRepository knjigaRepository;

    public Recenzija findOne(Long id){
        Optional<Recenzija> foundRecenzija = recenzijaRepository.findById(id);
        if (foundRecenzija.isPresent()) {
            return foundRecenzija.get();
        }
        return null;
    }

    public List<RecenzijaDto> findAll(){
        List<Recenzija> recenzijeList = recenzijaRepository.findAll();

        List<RecenzijaDto> dtos = new ArrayList<>();
        for(Recenzija recenzija : recenzijeList){
            RecenzijaDto dto = new RecenzijaDto(recenzija);
            dtos.add(dto);
        }
        return dtos;

    }

    public void add(RecenzijaAddDto recenzijaAddDto, Long stavkaPoliceId) throws ChangeSetPersister.NotFoundException {
        StavkaPolice stavka = stavkaPoliceService.findOneById(stavkaPoliceId);
        if (stavka.getRecenzija() == null) {
            Recenzija recenzija = new Recenzija();
            recenzija.setOcena(recenzijaAddDto.getOcena());
            recenzija.setDatum(recenzijaAddDto.getDatum());
            recenzija.setTekst(recenzijaAddDto.getTekst());
            Korisnik korisnik = korisnikRepository.getById(recenzijaAddDto.getKorisnikId());
            recenzija.setKorisnik(korisnik);
            Knjiga knjiga = stavka.getKnjiga();
            recenzija.setKnjiga(knjiga);


            List<Long> indeksiPolica = recenzijaAddDto.getPolice();
            for (Long indeks : indeksiPolica) {
                policaService.addKnjigaOnPolica(indeks, knjiga.getId());

            }

            // Save the Recenzija entity
            recenzija = recenzijaService.save(recenzija);

            stavka.setRecenzija(recenzija);
            stavkaPoliceService.save(stavka);

            // Associate the saved Recenzija with the Knjiga entity
            Set<Recenzija> recenzije = knjiga.getRecenzije();
            recenzije.add(recenzija);
            knjiga.setRecenzije(recenzije);

            // Save the Knjiga entity
            knjigaService.save(knjiga);
        }

    }


    public Recenzija updateRecenzija(Long id, UpdateRecDto updateRecDto) {
        Recenzija recenzija = findOne(id);

        recenzija.setOcena(updateRecDto.getOcena());
        recenzija.setDatum(updateRecDto.getDatum());
        recenzija.setTekst(updateRecDto.getTekst());

        return save(recenzija);
    }

    public void deleteRecenzija(Long id) throws ChangeSetPersister.NotFoundException {
        Recenzija recenzija = recenzijaRepository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        recenzijaRepository.delete(recenzija);
    }

    public void deleteRecenzijeByKnjigaId(Long knjigaId) {
        List<Recenzija> recenzije = recenzijaRepository.findAll();
        for (Recenzija recenzija : recenzije) {
            if(recenzija.getKnjiga().getId() == knjigaId) {
                recenzijaRepository.delete(recenzija);
            }
        }
    }

    public Recenzija save(Recenzija recenzija) { return recenzijaRepository.save(recenzija);}

    public Set<RecenzijaDto> searchRecenzijeKnjige(Long knjigaId) {
        KnjigaDto knjiga = knjigaService.findOne(knjigaId);
        Set<Recenzija> recenzije = knjiga.getRecenzije();
        Set<RecenzijaDto> dtos = new HashSet<>();

        for (Recenzija rec : recenzije) {
            RecenzijaDto dto = new RecenzijaDto(rec);
            dtos.add(dto);
        }

        return dtos;
    }

/*
        1. Trazenje jedne recenzije
        2. Izlistavanje svih recenzija
        3. Kreiranje recenzije
        4. Update recenzije
        5. Brisanje recenzije
        6. Save za cuvanje recenzije
 */
}
