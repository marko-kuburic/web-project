package com.INprojekat.WEB.service;

import com.INprojekat.WEB.dto.PolicaAddDto;
import com.INprojekat.WEB.dto.ZanrAddDto;
import com.INprojekat.WEB.entity.Polica;

import com.INprojekat.WEB.dto.KnjigaDto;
import com.INprojekat.WEB.dto.RecenzijaDto;
import com.INprojekat.WEB.dto.ZanrDto;
import com.INprojekat.WEB.entity.Knjiga;
import com.INprojekat.WEB.entity.Recenzija;

import com.INprojekat.WEB.entity.Zanr;
import com.INprojekat.WEB.repository.ZanrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ZanrService {

    @Autowired
    private ZanrRepository zanrRepository;

    public ZanrDto findOne(Long id){
        Optional<Zanr> foundZanr = zanrRepository.findById(id);
        if (foundZanr.isPresent()) {
            return new ZanrDto(foundZanr.get());
        }
        return null;
    }
    public List<ZanrDto> findAll(){
        List<Zanr> zanroviList = zanrRepository.findAll();

        List<ZanrDto> dtos = new ArrayList<>();
        for(Zanr zanr : zanroviList){
            ZanrDto dto = new ZanrDto(zanr);
            dtos.add(dto);
        }
        return dtos;
    }
    public Zanr create(ZanrAddDto zanrAddDto) {
        Zanr zanr = new Zanr();
        zanr.setNaziv(zanrAddDto.getNaziv());
        return save(zanr);
    }
    public Zanr save(Zanr zanr) { return zanrRepository.save(zanr); }

/*
        1. Trazenje jednog zanra
        2. Izlistavanje svih zanrova
        3. Kreiranje zanra
        3. Save za cuvanje zanra
 */
}
