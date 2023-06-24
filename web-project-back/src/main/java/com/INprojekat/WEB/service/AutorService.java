package com.INprojekat.WEB.service;

import com.INprojekat.WEB.dto.*;
import com.INprojekat.WEB.entity.Autor;
import com.INprojekat.WEB.entity.Knjiga;
import com.INprojekat.WEB.entity.Korisnik;
import com.INprojekat.WEB.entity.Polica;
import com.INprojekat.WEB.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AutorService {
    @Lazy
    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private PolicaService policaService;
    public Autor findOne(Long id) {
        return (Autor) autorRepository.findById(id).orElse(null);
    }

    public Autor create(AutorRegisterDto autorRegisterDto) {
        Autor autor = new Autor(autorRegisterDto);
        autor.setAktivnost(false);
        return save(autor);
    }

    public void addKnjiga(Long id, Knjiga knjiga){
        Autor autor = autorRepository.findAutorById(id);
        autor.addKnjiga(knjiga);
    }

    public List<KnjigaDto> getAutorKnjige(Long autorId){
        Autor autor = autorRepository.findAutorById(autorId);

        List<KnjigaDto> dtos = new ArrayList<>();
        for(Knjiga knjiga : autor.getKnjige()){
            KnjigaDto dto = new KnjigaDto(knjiga);
            dtos.add(dto);
        }
        return dtos;
    }

    public Autor updateAutor(Long id, UpdateDto updateDto){
        Autor autor = findOne(id);

        if(!updateDto.getLozinka().equals(updateDto.getNewlozinka())){
            throw new IllegalArgumentException("Lozinka se ne podudara");
        }

        autor.setIme(updateDto.getIme());
        autor.setPrezime(updateDto.getPrezime());
        autor.setProfilnaSlika(updateDto.getNaslovnaFotografija());
        autor.setDatumRodjenja(updateDto.getDatumRodjenja());
        autor.setOpis(updateDto.getOpis());

        if(autor.getLozinka() != null && !updateDto.getLozinka().isEmpty()) {

            autor.setLozinka(updateDto.getLozinka());
        }

        if(autor.getMail() != null && !updateDto.getMail().isEmpty()){

            autor.setMail(updateDto.getMail());
        }

        return save(autor);
    }
    public void activateAutor(Long id) {
        Set<Polica> police = new HashSet<>();
        Korisnik korisnik = korisnikService.findOne(id);
        Polica wantToRead = new Polica();
        wantToRead.setNaziv("Want To Read");
        wantToRead.setPrimarna(true);
        wantToRead.setKorisnik(korisnik);
        policaService.save(wantToRead);
        police.add(wantToRead);

        Polica currentlyReading = new Polica();
        currentlyReading.setNaziv("Currently Reading");
        currentlyReading.setPrimarna(true);
        currentlyReading.setKorisnik(korisnik);
        policaService.save(currentlyReading);
        police.add(currentlyReading);

        Polica read = new Polica();
        read.setNaziv("Read");
        read.setPrimarna(true);
        read.setKorisnik(korisnik);
        policaService.save(read);
        police.add(read);

        Autor autor = findOne(id);
        autor.getPolice().clear();
        autor.getPolice().addAll(police);
        autor.setAktivnost(true);
        save(autor);
    }

    public List<AutorDto> findAll(){
        List<Autor> autoriList = autorRepository.findAll();

        List<AutorDto> dtos = new ArrayList<>();
        for(Autor autor : autoriList){
            AutorDto dto = new AutorDto(autor);
            dtos.add(dto);
        }
        return dtos;
    }

    public Boolean existsMail(String mail) { return autorRepository.existsByMail(mail); }
    public Boolean existsLozinka(String lozinka) { return autorRepository.existsByLozinka(lozinka); }
    public Boolean existsKorisnickoIme(String korisnickoIme) { return autorRepository.existsByKorisnickoIme(korisnickoIme); }

    public Autor save(Autor autor) { return autorRepository.save(autor);}

/*
        1. Trazenje jednog autora
        2. Kreiranje autora
        3. Dodavanje knjiga autora
        4. Izlistavanje svih knjiga autora
        5. Update autora
        6. Provera postojanja maila
        7. Provera postojanja lozince
        8. Provera postojanja korisnickog imena
        9. Save za cuvanje autora
 */
}
