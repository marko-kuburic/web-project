package com.INprojekat.WEB.service;

import com.INprojekat.WEB.dto.PolicaAddDto;
import com.INprojekat.WEB.dto.PolicaDto;
import com.INprojekat.WEB.dto.RegisterDto;
import com.INprojekat.WEB.dto.ZanrDto;
import com.INprojekat.WEB.entity.Knjiga;
import com.INprojekat.WEB.entity.Korisnik;
import com.INprojekat.WEB.entity.Polica;
import com.INprojekat.WEB.entity.Zanr;
import com.INprojekat.WEB.dto.StavkaPoliceDto;
import com.INprojekat.WEB.entity.Knjiga;
import com.INprojekat.WEB.entity.Korisnik;
import com.INprojekat.WEB.entity.Polica;
import com.INprojekat.WEB.entity.StavkaPolice;
import com.INprojekat.WEB.repository.KnjigaRepository;
import com.INprojekat.WEB.repository.PolicaRepository;
import com.INprojekat.WEB.repository.StavkaPoliceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.*;

@Service
public class PolicaService {

    @Autowired
    private PolicaRepository policaRepository;
    @Autowired
    private  KnjigaRepository knjigaRepository;
    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private StavkaPoliceRepository stavkaPoliceRepository;

    public PolicaDto findOne(Long id){
        Optional<Polica> foundPolica = policaRepository.findById(id);
        if (foundPolica.isPresent()) {
            return new PolicaDto(foundPolica.get());
        }
        return null;
    }


    public Polica findOneById(Long id){
        Optional<Polica> foundPolica = policaRepository.findById(id);
        if (foundPolica.isPresent()) {
            return foundPolica.get();
        }
        return null;
    }
    public List<Polica> findAll(){
        return policaRepository.findAll();
    }

    public Set<PolicaDto> getPolice(Long id) {
        Korisnik korisnik = korisnikService.findOne(id);
        Set<Polica> police = korisnik.getPolice();
        Set<PolicaDto> dtos = new HashSet<>();
        for(Polica polica: police){
            PolicaDto dto = new PolicaDto(polica);
        }
        return dtos;
    }

    public void create(PolicaAddDto policaAddDto, Long id) {
        Polica polica;
        polica = new Polica();
        polica.setNaziv(policaAddDto.getNaziv());
        polica.setPrimarna(false);

        Korisnik korisnik = korisnikService.findOne(id);
        polica.setKorisnik(korisnik);
        Set<Polica> police = korisnik.getPolice();
        police.add(polica);
        korisnik.setPolice(police);
        korisnikService.save(korisnik);
    }

    public void main3(){
            Polica WantToRead = new Polica();
            WantToRead.setNaziv("Want To Read");
            WantToRead.setPrimarna(true);
            save(WantToRead);

            Polica CurrentlyReading = new Polica();
            CurrentlyReading.setNaziv("CurrentlyReading");
            CurrentlyReading.setPrimarna(true);
            save(CurrentlyReading);

            Polica Read = new Polica();
            Read.setNaziv("Read");
            Read.setPrimarna(true);
            save(Read);
    }


    public Long addKnjigaOnPolica(Long policaId, Long knjigaId) throws ChangeSetPersister.NotFoundException {
        Polica polica = policaRepository.findById(policaId).orElse(null);
        Knjiga knjiga = knjigaRepository.findById(knjigaId).orElse(null);
        StavkaPolice stavkaPolice = new StavkaPolice();

        stavkaPolice.setKnjiga(knjiga);
        stavkaPoliceRepository.save(stavkaPolice);
        polica.getStavkePolica().add(stavkaPolice);
        save(polica);

        return stavkaPolice.getId();
    }
    public boolean deletePolica(Long citalac_autorId,Long policaId) throws ChangeSetPersister.NotFoundException {
        Polica polica = policaRepository.findById(policaId)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        if(polica.isPrimarna() == false) {
            Korisnik korisnik = korisnikService.findOne(citalac_autorId);
            Set<Polica> police = korisnik.getPolice();
            police.remove(polica);
            policaRepository.delete(polica);
            korisnik.setPolice(police);
            korisnikService.save(korisnik);
            return true;
        }
        return false;
    }
    public Boolean existsPolicaInKorisnik(String naziv, Long id) {
        Set<Polica> police = korisnikService.findOne(id).getPolice();
        for(Polica polica: police){
            if(Objects.equals(polica.getNaziv(), naziv)){
                return true;
            }
        }
        return false;
    }
    public Polica save(Polica polica) { return policaRepository.save(polica);}

/*
        1. Trazenje jedne police
        2. Izlistavanje svih polica
        3. Izlistavanje svih polica korisnika
        4. Kreiranje nove police
        5. Kreiranje 3 main police
        6. Dodavanje knjige na policu
        7. Brisanje police
        8. Trazenje polica istog imena
        9. Cuvanje police
 */
}
