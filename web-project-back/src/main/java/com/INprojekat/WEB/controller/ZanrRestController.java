package com.INprojekat.WEB.controller;

import com.INprojekat.WEB.dto.KnjigaDto;
import com.INprojekat.WEB.dto.RecenzijaDto;
import com.INprojekat.WEB.dto.ZanrDto;
import com.INprojekat.WEB.entity.Recenzija;
import com.INprojekat.WEB.entity.Zanr;
import com.INprojekat.WEB.service.KnjigaService;
import com.INprojekat.WEB.service.ZanrService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ZanrRestController {

    @Autowired
    private ZanrService zanrService;
    @Autowired
    private KnjigaService knjigaService;


    @GetMapping("/api/zanr/{id}")
    public ResponseEntity<ZanrDto> getZanr(@PathVariable Long id){

        ZanrDto zanrDto = zanrService.findOne(id);

        return ResponseEntity.ok(zanrDto);
    }
    @GetMapping("/api/zanrovi")
    public ResponseEntity<List<ZanrDto>> getZanrovi(HttpSession session){
        List<ZanrDto> dtos = zanrService.findAll();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("api/search-knjigeByZanr/{string}")
    public ResponseEntity<?> searchKnjigeByZanr(@PathVariable String string) {
        List<KnjigaDto> dtos = knjigaService.searchKnjigeByZanr(string);
        if (dtos.isEmpty()) {
            return ResponseEntity.badRequest().body("Ne postoji");
        } else {
            return ResponseEntity.ok(dtos);
        }
    }

}
