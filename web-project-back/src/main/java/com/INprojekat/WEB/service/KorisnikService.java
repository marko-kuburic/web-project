package com.INprojekat.WEB.service;
import com.INprojekat.WEB.dto.*;
import com.INprojekat.WEB.dto.RegisterDto;
import com.INprojekat.WEB.dto.RegisterDto;
import com.INprojekat.WEB.dto.KorisnikDto;
import com.INprojekat.WEB.dto.RegisterDto;
import com.INprojekat.WEB.dto.UpdateDto;
import com.INprojekat.WEB.dto.KorisnikDto;
import com.INprojekat.WEB.dto.RegisterDto;
import com.INprojekat.WEB.dto.UpdateDto;
import com.INprojekat.WEB.entity.Knjiga;
import com.INprojekat.WEB.entity.Korisnik;
import com.INprojekat.WEB.entity.Zanr;
import com.INprojekat.WEB.entity.Polica;
import com.INprojekat.WEB.repository.KorisnikRepository;
import jakarta.persistence.Id;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class KorisnikService {

    @Autowired
    private KorisnikRepository korisnikRepository;

    @Autowired
    private PolicaService policaService;

    public Korisnik findOne(Long id){
        Optional<Korisnik> foundKorisnik = korisnikRepository.findById(id);
        if (foundKorisnik.isPresent()) {
            return foundKorisnik.get();
        }
        return null;
    }
    public List<KorisnikDto> findAll(){
        List<Korisnik> korisniciList = korisnikRepository.findAll();

        List<KorisnikDto> dtos = new ArrayList<>();
        for(Korisnik korisnik : korisniciList){
            KorisnikDto dto = new KorisnikDto(korisnik);
            dtos.add(dto);
        }
        return dtos;

    }


    public Korisnik login(String username, String password, HttpSession session) {
        Korisnik korisnik = korisnikRepository.getByMail(username);
        if (korisnik == null || !korisnik.getLozinka().equals(password)) {
            return null;
        }

        session.setAttribute("employee", korisnik);
        return korisnik;
    }


    public Korisnik create(RegisterDto registerDto) {
        Korisnik korisnik = new Korisnik();
        korisnik.setIme(registerDto.getIme());
        korisnik.setPrezime(registerDto.getPrezime());
        korisnik.setKorisnickoIme(registerDto.getKorisnickoIme());
        korisnik.setMail(registerDto.getMail());
        korisnik.setLozinka(registerDto.getLozinka());
        korisnik.setUloga(Korisnik.Uloga.CITALAC);

        // Save the Korisnik entity
        korisnik = save(korisnik);

        // Create and associate the Polica entities
        Set<Polica> police = new HashSet<>();

        Polica wantToRead = new Polica();
        wantToRead.setNaziv("Want To Read");
        wantToRead.setPrimarna(true);
        wantToRead.setKorisnik(korisnik);
        police.add(wantToRead);

        Polica currentlyReading = new Polica();
        currentlyReading.setNaziv("Currently Reading");
        currentlyReading.setPrimarna(true);
        currentlyReading.setKorisnik(korisnik);
        police.add(currentlyReading);

        Polica read = new Polica();
        read.setNaziv("Read");
        read.setPrimarna(true);
        read.setKorisnik(korisnik);
        police.add(read);

        // Save the Polica entities
        for (Polica polica : police) {
            policaService.save(polica);
        }

        // Set the Polica collection in the Korisnik entity
        korisnik.setPolice(police);

        return korisnik;
    }


    public Korisnik updateUser(Long id, UpdateDto updateDto){
        Korisnik korisnik = findOne(id);

        if(updateDto.getLozinka().equals(updateDto.getNewlozinka())){
            throw new IllegalArgumentException("Lozinka se ne podudara");
        }

        korisnik.setIme(updateDto.getIme());
        korisnik.setPrezime(updateDto.getPrezime());
        korisnik.setProfilnaSlika(updateDto.getNaslovnaFotografija());
        korisnik.setDatumRodjenja(updateDto.getDatumRodjenja());
        korisnik.setOpis(updateDto.getOpis());
        korisnik.setUloga(Korisnik.Uloga.CITALAC);

        if(korisnik.getLozinka() != null && !updateDto.getLozinka().isEmpty()) {

            korisnik.setLozinka(updateDto.getLozinka());
        }

        if(korisnik.getMail() != null && !updateDto.getMail().isEmpty()){

            korisnik.setMail(updateDto.getMail());
        }

        return save(korisnik);
    }
    public Korisnik save(Korisnik korisnik) { return korisnikRepository.save(korisnik); }

    public Boolean existsMail(String mail) { return korisnikRepository.existsByMail(mail); }
    public Boolean existsLozinka(String lozinka) { return korisnikRepository.existsByLozinka(lozinka); }
    public Boolean existsKorisnickoIme(String korisnickoIme) { return korisnikRepository.existsByKorisnickoIme(korisnickoIme); }

    public List<KorisnikDto> searchUsers(String string){
        List<KorisnikDto> korisnici = findAll();
        List<KorisnikDto> korisniciIzdvojeno = new ArrayList<>();
        for(KorisnikDto dto : korisnici){
            if(dto.getIme().toLowerCase().contains(string.toLowerCase()) || dto.getPrezime().toLowerCase().contains(string.toLowerCase()) || dto.getKorisnickoIme().toLowerCase().contains(string.toLowerCase())){
                korisniciIzdvojeno.add(dto);
            }
        }
        return korisniciIzdvojeno;
    }

/*
        1. Trazenje jednog korisnika
        2. Izlistavanje svih korisnika
        3. Kreiranje korisnika
        4. Update korisnika
        5. Provera postojanja maila
        6. Provera postojanja lozinke
        7. Provera postojanja korisnickog imena
        8. Save za cuvanje korisnika
        9. Pretraga korisnika
 */
}
