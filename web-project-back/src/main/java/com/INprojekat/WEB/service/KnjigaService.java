package com.INprojekat.WEB.service;

import com.INprojekat.WEB.dto.KnjigaAutorDto;
import com.INprojekat.WEB.dto.KnjigaDto;
import com.INprojekat.WEB.dto.UpdateKnjigaDto;
import com.INprojekat.WEB.entity.*;
import com.INprojekat.WEB.repository.AutorRepository;
import com.INprojekat.WEB.repository.KnjigaRepository;
import com.INprojekat.WEB.repository.ZanrRepository;
import jakarta.persistence.GeneratedValue;

import com.INprojekat.WEB.dto.*;
import com.INprojekat.WEB.entity.*;
import com.INprojekat.WEB.repository.KnjigaRepository;
import com.INprojekat.WEB.repository.PolicaRepository;
import com.INprojekat.WEB.repository.RecenzijaRepository;
import com.INprojekat.WEB.repository.StavkaPoliceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class KnjigaService {

    @Autowired
    private KnjigaRepository knjigaRepository;
    @Autowired
    private KnjigaService knjigaService;
    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private ZanrRepository zanrRepository;
    @Autowired
    private RecenzijaRepository recenzijaRepository;

    @Autowired
    private StavkaPoliceRepository stavkaPoliceRepository;

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private PolicaService policaService;

    @Autowired
    private AutorService autorService;
    @Autowired
    private PolicaRepository policaRepository;

    @Autowired
    private StavkaPoliceService stavkaPoliceService;

    @Autowired
    private RecenzijaService recenzijaService;

    private Knjiga findOneById(Long knjigaId) {
        Optional<Knjiga> foundKnjiga = knjigaRepository.findById(knjigaId);
        if (foundKnjiga.isPresent()) {
            return foundKnjiga.get();
        }
        return null;
    }


    public KnjigaDto findOne(Long id){
        Optional<Knjiga> foundKnjiga = knjigaRepository.findById(id);
        if (foundKnjiga.isPresent()) {
            return new KnjigaDto(foundKnjiga.get());
        }
        return null;
    }

    public List<KnjigaDto> findAll(){
        List<Knjiga> knjigeList = knjigaRepository.findAll();

        List<KnjigaDto> dtos = new ArrayList<>();
        for(Knjiga knjiga : knjigeList){
            KnjigaDto dto = new KnjigaDto(knjiga);
            dtos.add(dto);
        }
        return dtos;
    }
    public Knjiga create(Long autorId, KnjigaAutorDto knjigaAutorDto) {
        Autor autor = autorService.findOne(autorId);
        Knjiga knjiga = new Knjiga();
        knjiga.setNaslov(knjigaAutorDto.getNaslov());
        knjiga.setNaslovnaFotografija(knjigaAutorDto.getNaslovnaFotografija());
        knjiga.setISBN(knjigaAutorDto.getISBN());
        knjiga.setBrojStrana(knjigaAutorDto.getBrojStrana());
        knjiga.setDatumObjavljivanja(knjigaAutorDto.getDatumObjavljivanja());
        knjiga.setOpis(knjigaAutorDto.getOpis());
        knjiga.setZanr(zanrRepository.findZanrById(knjigaAutorDto.getZanrId()));
        knjiga.setAutor(autorRepository.findAutorById(knjigaAutorDto.getAutorId()));

        return save(knjiga);
    }

    public Knjiga createKnjigaAdmin(KnjigaAutorDto knjigaAutorDto) {
        Knjiga knjiga = new Knjiga();
        knjiga.setNaslov(knjigaAutorDto.getNaslov());
        knjiga.setNaslovnaFotografija(knjigaAutorDto.getNaslovnaFotografija());
        knjiga.setISBN(knjigaAutorDto.getISBN());
        knjiga.setBrojStrana(knjigaAutorDto.getBrojStrana());
        knjiga.setDatumObjavljivanja(knjigaAutorDto.getDatumObjavljivanja());
        knjiga.setOpis(knjigaAutorDto.getOpis());
        knjiga.setZanr(zanrRepository.findZanrById(knjigaAutorDto.getZanrId()));
        knjiga.setAutor(autorRepository.findAutorById(knjigaAutorDto.getAutorId()));

        return save(knjiga);
    }

    public Knjiga updateKnjiga(Long autorId, Long knjigaId, UpdateKnjigaDto updateKnjigaDto){
        Optional<Knjiga> knjiga = knjigaRepository.findById(knjigaId);
        if(knjiga.get().getAutor().getId() != autorId){
            return null;
        }
        knjiga.get().setNaslov(updateKnjigaDto.getNaslov());
        knjiga.get().setNaslovnaFotografija(updateKnjigaDto.getNaslovnaFotografija());

        String updatedISBN = updateKnjigaDto.getISBN();
        if (updatedISBN != null && !updatedISBN.isEmpty()) {
            knjiga.get().setISBN(updatedISBN);
        }
        knjiga.get().setDatumObjavljivanja(updateKnjigaDto.getDatumObjavljivanja());
        knjiga.get().setBrojStrana(updateKnjigaDto.getBrojStrana());
        knjiga.get().setOpis(updateKnjigaDto.getOpis());
        knjiga.get().setZanr(zanrRepository.findZanrById(updateKnjigaDto.getZanrId()));
        return save(knjiga.get());
    }
    public Knjiga updateKnjigaAdmin(Long knjigaId, UpdateKnjigaDto updateKnjigaDto){
        Optional<Knjiga> knjiga = knjigaRepository.findById(knjigaId);
        knjiga.get().setNaslov(updateKnjigaDto.getNaslov());
        knjiga.get().setNaslovnaFotografija(updateKnjigaDto.getNaslovnaFotografija());

        String updatedISBN = updateKnjigaDto.getISBN();
        if (updatedISBN != null && !updatedISBN.isEmpty()) {
            knjiga.get().setISBN(updatedISBN);
        }
        knjiga.get().setDatumObjavljivanja(updateKnjigaDto.getDatumObjavljivanja());
        knjiga.get().setBrojStrana(updateKnjigaDto.getBrojStrana());
        knjiga.get().setOpis(updateKnjigaDto.getOpis());
        knjiga.get().setAutor(autorRepository.findAutorById(updateKnjigaDto.getAutorId()));
        knjiga.get().setZanr(zanrRepository.findZanrById(updateKnjigaDto.getZanrId()));
        return save(knjiga.get());
    }

    public void deleteKnjiga(Long citalac_autor_Id, Long policaId, Long knjigaId) throws ChangeSetPersister.NotFoundException {
        Knjiga knjiga = knjigaRepository.findById(knjigaId)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        Korisnik korisnik = korisnikService.findOne(citalac_autor_Id);
        Polica polica = policaService.findOneById(policaId);
        Set<Polica> korisnikovePolice = korisnik.getPolice();
        if(polica.isPrimarna()){
            if(polica.getNaziv().equals("Read")){
                for (Polica p : korisnikovePolice) {
                    if (p.getStavkePolica().stream().anyMatch(stavka -> stavka.getKnjiga().equals(knjiga))) {
                        for (StavkaPolice stavka : p.getStavkePolica()) {
                            if (stavka.getKnjiga().equals(knjiga)) {
                                stavkaPoliceService.deleteStavkaPolice(p.getId(), stavka.getId());
                            }
                        }
                    }
                }
            }
            else {
                for (Polica p : korisnikovePolice) {
                    if (p.getStavkePolica().stream().anyMatch(stavka -> stavka.getKnjiga().equals(knjiga))) {
                        for (StavkaPolice stavka : p.getStavkePolica()) {
                            if (stavka.getKnjiga().equals(knjiga)) {
                                stavka.setKnjiga(null);
                            }
                        }
                    }
                }
            }
        }
        else {
            for (StavkaPolice stavka : polica.getStavkePolica()) {
                if (stavka.getKnjiga().equals(knjiga)) {
                    stavkaPoliceService.deleteStavkaPolice(policaId, stavka.getId());
                }
            }
        }
    }

/*
public void deleteKnjiga(Long citalac_autor_Id, Long policaId, Long knjigaId) throws ChangeSetPersister.NotFoundException {
        Knjiga knjiga = knjigaRepository.findById(knjigaId)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        Korisnik korisnik = korisnikService.findOne(citalac_autor_Id);
        Polica polica = policaService.findOneById(policaId);
        Set<Polica> korisnikovePolice = korisnik.getPolice();
        if(polica.isPrimarna()){
            if(polica.getNaziv().equals("Read")){
                for (Polica p : korisnikovePolice) {
                    if (p.getStavkePolica().stream().anyMatch(stavka -> stavka.getKnjiga().equals(knjiga))) {
                        for (StavkaPolice stavka : p.getStavkePolica()) {
                            if (stavka.getKnjiga().equals(knjiga)) {
                                stavkaPoliceService.deleteStavkaPolice(p.getId(), stavka.getId());
                            }
                        }
                    }
                }
            }
            else {
                for (Polica p : korisnikovePolice) {
                    if (p.getStavkePolica().stream().anyMatch(stavka -> stavka.getKnjiga().equals(knjiga))) {
                        for (StavkaPolice stavka : p.getStavkePolica()) {
                            if (stavka.getKnjiga().equals(knjiga)) {
                                stavka.setKnjiga(null);
                            }
                        }
                    }
                }
            }
        }
        else {
            for (StavkaPolice stavka : polica.getStavkePolica()) {
                if (stavka.getKnjiga().equals(knjiga)) {
                    stavkaPoliceService.deleteStavkaPolice(policaId, stavka.getId());
                }
            }
        }
    }
 */

    public boolean findKnjigaOnPrimarnaPolica(Long citalacId, Long knjigaId){
        Korisnik korisnik = korisnikService.findOne(citalacId);
        Set<Polica> korisnikovePolice = korisnik.getPolice();

        for (Polica p : korisnikovePolice) {
            if(p.isPrimarna()){
                for (StavkaPolice stavka : p.getStavkePolica()) {
                    if (stavka.getKnjiga().getId() == knjigaId) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void deleteKnjigaAdmin(Long id) throws ChangeSetPersister.NotFoundException {
        Knjiga knjiga = knjigaRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        if (knjiga.getRecenzije().isEmpty()) {
            List<Polica> police = policaRepository.findAll();
            for(Polica polica: police){
                for(StavkaPolice stavka : polica.getStavkePolica()){
                    if(stavka.getKnjiga().getId() == id){
                        stavkaPoliceService.deleteStavkaPolice(polica.getId(), stavka.getId());
                    }
                }
            }
            knjigaRepository.delete(knjiga);
        }
    }
    public Boolean existsKnjiga(String naziv) { return knjigaRepository.existsByNaslov(naziv); }

    public Knjiga save(Knjiga knjiga) { return knjigaRepository.save(knjiga); }

    public List<KnjigaDto> searchKnjige(String string){
        List<KnjigaDto> knjige = findAll();
        List<KnjigaDto> knjigeIzdvojeno = new ArrayList<>();
        for(KnjigaDto dto : knjige){
            if(dto.getNaslov().toLowerCase().contains(string.toLowerCase())){
                knjigeIzdvojeno.add(dto);
            }
        }
        return knjigeIzdvojeno;
    }
    public List<KnjigaDto> searchKnjigeByZanr(String string){
        List<KnjigaDto> knjige = findAll();
        List<KnjigaDto> knjigeIzdvojeno = new ArrayList<>();
        for(KnjigaDto dto : knjige){
            if(dto.getZanr().getNaziv().toLowerCase().contains(string.toLowerCase())){
                knjigeIzdvojeno.add(dto);
            }
        }
        return knjigeIzdvojeno;
    }


/*
        1. Trazenje jedne knjige
        2. Izlistavanje svih knjiga
        3. Kreiranje knjige od strane autora
        4. Kreiranje knjige od strane admina
        5. Update knjige od strane autora
        6. Update knjige od strane admina1
        7. Brisanje knjige
        8. Save za cuvanje knjige
 */
}

