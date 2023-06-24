package com.INprojekat.WEB.service;

import com.INprojekat.WEB.dto.StavkaPoliceAddDto;
import com.INprojekat.WEB.dto.StavkaPoliceDto;
import com.INprojekat.WEB.dto.ZanrDto;
import com.INprojekat.WEB.entity.*;
import com.INprojekat.WEB.entity.StavkaPolice;
import com.INprojekat.WEB.repository.PolicaRepository;
import com.INprojekat.WEB.repository.StavkaPoliceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StavkaPoliceService {

    @Autowired
    private StavkaPoliceRepository stavkaPoliceRepository;

    @Autowired
    private PolicaService policaService;
    @Autowired
    private RecenzijaService recenzijaService;


    public StavkaPoliceDto findOne(Long id){
        Optional<StavkaPolice> foundStavka = stavkaPoliceRepository.findById(id);
        if (foundStavka.isPresent()) {
            return new StavkaPoliceDto(foundStavka.get());
        }
        return null;
    }
    public StavkaPolice findOneById(Long id){
        Optional<StavkaPolice> stavkaPolice = stavkaPoliceRepository.findById(id);
        if (stavkaPolice.isPresent()) {
            return stavkaPolice.get();
        }
        return null;
    }

    public List<StavkaPoliceDto> findAll(){
        List<StavkaPolice> stavkeList = stavkaPoliceRepository.findAll();

        List<StavkaPoliceDto> dtos = new ArrayList<>();
        for(StavkaPolice stavkaPolice : stavkeList){
            StavkaPoliceDto dto = new StavkaPoliceDto(stavkaPolice);
            dtos.add(dto);
        }
        return dtos;

    }

    public void create(StavkaPoliceAddDto stavkaPoliceAddDto,Long policaId) {
        StavkaPolice stavkaPolice = new StavkaPolice();
        stavkaPolice.setKnjiga(stavkaPoliceAddDto.getKnjiga());
        stavkaPolice.setRecenzija(stavkaPoliceAddDto.getRecenzija());

        Polica polica = policaService.findOneById(policaId);
        Set<StavkaPolice> stavkePolice = polica.getStavkePolica();
        stavkePolice.add(stavkaPolice);
        polica.setStavkePolica(stavkePolice);
        policaService.save(polica);

    }


//    public void deleteStavkaPolice(Long policaId,Long stavkaId) throws ChangeSetPersister.NotFoundException {
//        StavkaPolice stavkaPolice = stavkaPoliceRepository.findById(stavkaId)
//                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
//        Polica polica = policaService.findOneById(policaId);
//        Set<StavkaPolice> stavkePolice = polica.getStavkePolica();
//        Recenzija recenzija = stavkaPolice.getRecenzija();
//        if(recenzija != null) {
//            recenzijaService.deleteRecenzija(recenzija.getId());
//        }
//        stavkePolice.remove(stavkaPolice);
//        stavkaPoliceRepository.delete(stavkaPolice);
//        polica.setStavkePolica(stavkePolice);
//        policaService.save(polica);
//
//    }

    public void deleteStavkaPolice(Long policaId, Long stavkaId) throws ChangeSetPersister.NotFoundException {
        StavkaPolice stavkaPolice = stavkaPoliceRepository.findById(stavkaId)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        Polica polica = policaService.findOneById(policaId);

        // Uklanjanje veze sa Recenzija
        Recenzija recenzija = stavkaPolice.getRecenzija();
        recenzija.getStavkePolice().remove(stavkaPolice);

        // Uklanjanje veze sa Polica
        polica.getStavkePolica().remove(stavkaPolice);

        // Brisanje stavke
        stavkaPoliceRepository.delete(stavkaPolice);
    }

    public StavkaPolice save(StavkaPolice stavka) { return  stavkaPoliceRepository.save(stavka);}

/*
        1. Trazenje jedne stavke polica
        2. Izlistavanje svih stavki polica
        3. Kreiranje stavke polica
        4. Brisanje stavke polica
        5. Save za cuvanje stavke polica
 */
}
