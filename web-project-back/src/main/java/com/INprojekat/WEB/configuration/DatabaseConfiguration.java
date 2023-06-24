package com.INprojekat.WEB.configuration;

import com.INprojekat.WEB.entity.*;
import com.INprojekat.WEB.repository.*;
import com.INprojekat.WEB.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Configuration
public class DatabaseConfiguration {
    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private KnjigaRepository knjigaRepository;
    @Autowired
    private KorisnikRepository korisnikRepository;
    @Autowired
    private PolicaRepository policaRepository;
    @Autowired
    private RecenzijaRepository recenzijaRepository;
    @Autowired
    private StavkaPoliceRepository stavkaPoliceRepository;
    @Autowired
    private ZahtevZaAktiavcijuNalogaAutoraRepository zahtevZaAktiavcijuNalogaAutoraRepository;
    @Autowired
    private ZanrRepository zanrRepository;

    @Bean
    public boolean instantiate() throws ParseException {
        Korisnik autorK1 = new Korisnik("Dzordz", "Hegel", "hegi", "hegel@gmail.com", "hegelhegel",  "/hegel", "lud", Korisnik.Uloga.AUTOR);
        Korisnik autorK2 = new Korisnik("Dzoni", "Piksla", "piksla", "dzonipiksla@gmail.com", "otvarajbolan",  "/slika1.jbg", "brz", Korisnik.Uloga.AUTOR);

        Autor autor1 = new Autor(autorK1, false);
        Autor autor2 = new Autor(autorK2, false);

        Korisnik admin = new Korisnik("Milkica", " ", "milkicacvece", "milkica@gmail.com", "milkica",  "/cvece", "nema da nema", Korisnik.Uloga.ADMINISTRATOR);
        korisnikRepository.saveAll(
                List.of(admin)
        );
        autorRepository.saveAll(
                List.of(autor1, autor2)
        );

        Zanr zanr1 = new Zanr("Drama");
        Zanr zanr2 = new Zanr("Triler");
        Zanr zanr3 = new Zanr("Haos");
        zanrRepository.saveAll(List.of(zanr1, zanr2, zanr3));

        DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        Date d1 = df.parse("11-09-2001"); // for example, today's date
        Date d2 = df.parse("01-01-1989"); // use your own dates, of course

        Knjiga knjiga1 = new Knjiga("Smisao zivota u lirici Ane Nikolic", "/AnaNikolic/slike/Albumi/Milion Dolara", "978-2-3151-8866-6", 365, "Krunsko delo svih filosofskih traktata sazdano kao egzistencijalistko resenje svih pitanja postpostmoderne", d1, autor1, zanr1);
        Knjiga knjiga2 = new Knjiga("Srpsko-srpski recnik", "knjiga.jpg", "978-1-7697-1292-2", 3000, "Recnik.", d2 , autor2, zanr2);
        Knjiga knjiga3 = new Knjiga("Jevandjelje po Marini Tucakovic", "MarinaTucakovic.jpg", "978-5-6359-5740-0", 60, "Blaga vest nase najdarovitije tekstospisateljice.", d2 , autor1, zanr3);
        knjigaRepository.saveAll(List.of(knjiga1, knjiga2, knjiga3));
        return true;
    }
}

