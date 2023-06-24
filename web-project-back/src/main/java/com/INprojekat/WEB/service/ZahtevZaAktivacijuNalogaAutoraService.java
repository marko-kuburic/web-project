package com.INprojekat.WEB.service;

import com.INprojekat.WEB.dto.ZahtevDto;
import com.INprojekat.WEB.dto.ZahtevZaAktivacijuNalogaAutoraDto;
import com.INprojekat.WEB.entity.*;
import com.INprojekat.WEB.repository.AutorRepository;
import com.INprojekat.WEB.repository.KorisnikRepository;
import com.INprojekat.WEB.repository.ZahtevZaAktiavcijuNalogaAutoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ZahtevZaAktivacijuNalogaAutoraService {

    @Autowired
    private ZahtevZaAktiavcijuNalogaAutoraRepository zahtevZaAktiavcijuNalogaAutoraRepository;

    @Autowired
    private KorisnikRepository korisnikRepository;
    @Autowired
    AutorRepository autorRepository;
    public ZahtevZaAktivacijuNalogaAutoraDto findOne(Long id){
        Optional<ZahtevZaAktivacijuNalogaAutora> foundZahtev = zahtevZaAktiavcijuNalogaAutoraRepository.findById(id);
        if (foundZahtev.isPresent()) {
            return new ZahtevZaAktivacijuNalogaAutoraDto(foundZahtev.get());
        }
        return null;
    }
    public List<ZahtevZaAktivacijuNalogaAutoraDto> findAll() {
        List<ZahtevZaAktivacijuNalogaAutora> zahtevi = zahtevZaAktiavcijuNalogaAutoraRepository.findAll();

        List<ZahtevZaAktivacijuNalogaAutoraDto> dtos = new ArrayList<>();
        for(ZahtevZaAktivacijuNalogaAutora zahtev : zahtevi){
            ZahtevZaAktivacijuNalogaAutoraDto dto = new ZahtevZaAktivacijuNalogaAutoraDto(zahtev);
            dtos.add(dto);
        }
        return dtos;
    }

    public ZahtevZaAktivacijuNalogaAutora saveDto(ZahtevZaAktivacijuNalogaAutoraDto zanaDto) {
        ZahtevZaAktivacijuNalogaAutora zana = new ZahtevZaAktivacijuNalogaAutora(zanaDto);
        return zahtevZaAktiavcijuNalogaAutoraRepository.save(zana);
    }
    public ZahtevZaAktivacijuNalogaAutora create(ZahtevDto zahtev) {
        if(autorRepository.findAutorById(zahtev.getKorisnik_id()).getAktivnost()){return null;}
        ZahtevZaAktivacijuNalogaAutora zana = new ZahtevZaAktivacijuNalogaAutora();
        zana.setEmail(zahtev.getMail());
        zana.setTelefon(zahtev.getBrojTelefona());
        zana.setPoruka(zahtev.getDodatnaPoruka());
        zana.setStatus(ZahtevZaAktivacijuNalogaAutora.Status.na_cekanju);
        zana.setKorisnik(korisnikRepository.findById(zahtev.getKorisnik_id()).get());
        if(korisnikRepository.findById(zahtev.getKorisnik_id()).get().getUloga() != Korisnik.Uloga.AUTOR)
            return null;
        return save(zana);
    }
    public ZahtevZaAktivacijuNalogaAutora save(ZahtevZaAktivacijuNalogaAutora zana) { return zahtevZaAktiavcijuNalogaAutoraRepository.save(zana); }

/*
        1. Trazenje jednog zahteva za aktivaciju
        2. Izlistavanje svih zahteva za aktivaciju
        3. Cuvanje stiglog zahteva za aktivaciju
        4. Kreiranje zahteva za aktivaciju
        5. Save za cuvanje zahteva za aktivaciju
 */
}
