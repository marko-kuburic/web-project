package com.INprojekat.WEB.controller;

import com.INprojekat.WEB.dto.PolicaAddDto;
import com.INprojekat.WEB.dto.StavkaPoliceAddDto;
import com.INprojekat.WEB.dto.StavkaPoliceDto;
import com.INprojekat.WEB.dto.ZanrDto;
import com.INprojekat.WEB.entity.Korisnik;
import com.INprojekat.WEB.service.StavkaPoliceService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StavkaPoliceRestController {

    @Autowired
    private StavkaPoliceService stavkaPoliceService;

    @GetMapping("/api/stavke-police/{id}")
    public ResponseEntity<StavkaPoliceDto> getStavka(@PathVariable Long stavkaPoliceId){

        StavkaPoliceDto stavkaPoliceDto = stavkaPoliceService.findOne(stavkaPoliceId);

        return ResponseEntity.ok(stavkaPoliceDto);
    }
    @GetMapping("/api/stavke-police")
    public ResponseEntity<List<StavkaPoliceDto>> getStavke(HttpSession session){
        List<StavkaPoliceDto> dtos = stavkaPoliceService.findAll();
        return ResponseEntity.ok(dtos);
    }
    @PostMapping("/api/citalac/polica/{policaId}/stavka-police-add")
    public ResponseEntity<?> addStavkaPolice(@RequestBody StavkaPoliceAddDto stavkaPoliceAddDto, @PathVariable Long policaId, HttpSession session) {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("employee");
        if(loggedKorisnik.getUloga() == Korisnik.Uloga.CITALAC  || loggedKorisnik.getUloga() == Korisnik.Uloga.AUTOR ){
            stavkaPoliceService.create(stavkaPoliceAddDto, policaId);
            return new ResponseEntity<>("Stavka police added successfully", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("You are not administrator", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/api/citalac/polica/{policaId}/stavka-police/{id}")
    public ResponseEntity<?> deleteStavkaPolice(@PathVariable Long policaId,@PathVariable Long id, HttpSession session) throws ChangeSetPersister.NotFoundException {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("employee");
        if(loggedKorisnik.getUloga() == Korisnik.Uloga.CITALAC  || loggedKorisnik.getUloga() == Korisnik.Uloga.AUTOR ){
            stavkaPoliceService.deleteStavkaPolice(policaId, id);
            return new ResponseEntity<>("Stavka police deleted successfully", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("You are not administrator", HttpStatus.BAD_REQUEST);
        }
    }
}
