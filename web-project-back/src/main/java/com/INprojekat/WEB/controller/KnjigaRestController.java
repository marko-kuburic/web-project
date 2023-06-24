package com.INprojekat.WEB.controller;

import com.INprojekat.WEB.dto.*;
import com.INprojekat.WEB.service.KorisnikService;
import com.INprojekat.WEB.entity.Knjiga;
import com.INprojekat.WEB.entity.Korisnik;
import com.INprojekat.WEB.service.KnjigaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class KnjigaRestController {

    @Autowired
    private KnjigaService knjigaService;

    @GetMapping("/api/knjige")
    public ResponseEntity<List<KnjigaDto>> getKnjige(){
        List<KnjigaDto> dtos = knjigaService.findAll();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/api/knjige/{id}")
    public ResponseEntity<KnjigaDto> getKnjiga(@PathVariable Long id){

        KnjigaDto knjigaDto = knjigaService.findOne(id);

        return ResponseEntity.ok(knjigaDto);
    }

    @DeleteMapping("/api/citalac/polica/{policaId}/knjiga/{knjigaId}")
    public ResponseEntity<?> deleteKnjigaCitalac(@PathVariable Long policaId,@PathVariable Long knjigaId, HttpSession session) throws ChangeSetPersister.NotFoundException {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("employee");
        if(loggedKorisnik.getUloga() == Korisnik.Uloga.CITALAC){
            knjigaService.deleteKnjiga(loggedKorisnik.getId(), policaId,knjigaId);
            return new ResponseEntity<>("Book deleted successfully", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("You are not administrator", HttpStatus.OK);
        }
    }

    @DeleteMapping("/api/autor/polica/{policaId}/knjiga/{knjigaId}")
    public ResponseEntity<?> deleteKnjigaAutor(@PathVariable Long policaId,@PathVariable Long knjigaId, HttpSession session) throws ChangeSetPersister.NotFoundException {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("employee");
        if(loggedKorisnik.getUloga() == Korisnik.Uloga.AUTOR){
            knjigaService.deleteKnjiga(loggedKorisnik.getId(), policaId,knjigaId);
            return new ResponseEntity<>("Book deleted successfully", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("You are not administrator", HttpStatus.OK);
        }
    }
    @DeleteMapping("/api/admin/{knjigaId}")
    public ResponseEntity<?> deleteKnjigaAdmin(@PathVariable Long knjigaId, HttpSession session) throws ChangeSetPersister.NotFoundException {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("employee");
        if(loggedKorisnik.getUloga() == Korisnik.Uloga.ADMINISTRATOR){
            knjigaService.deleteKnjigaAdmin(knjigaId);
            return new ResponseEntity<>("Book deleted successfully", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("You are not administrator", HttpStatus.OK);
        }
    }

    @GetMapping("api/search-knjige/{name}")
    public ResponseEntity<?> searchKnjige(@PathVariable String name) {
        List<KnjigaDto> dtos = knjigaService.searchKnjige(name);

        if (dtos.isEmpty()) {
            return ResponseEntity.ok(dtos);
        } else {
            return ResponseEntity.ok(dtos);
        }
    }


}


