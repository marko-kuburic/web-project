package com.INprojekat.WEB.controller;

import com.INprojekat.WEB.dto.KnjigaDto;
import com.INprojekat.WEB.dto.LoginDto;
import com.INprojekat.WEB.dto.RegisterDto;
import com.INprojekat.WEB.dto.*;
import com.INprojekat.WEB.entity.*;
import com.INprojekat.WEB.repository.KorisnikRepository;
import com.INprojekat.WEB.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.*;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@CrossOrigin
@RestController
public class KorisnikRestController {
    @Autowired
    private KorisnikService korisnikService;
    @Autowired
    private PolicaService policaService;
    @Autowired
    private AutorService autorService;
    @Autowired
    private ZanrService zanrService;
    @Autowired
    private KnjigaService knjigaService;
    @Autowired
    private ZahtevZaAktivacijuNalogaAutoraService zahtevZaAktivacijuNalogaAutoraService;
    @Autowired
    private RecenzijaService recenzijaService;
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/api/")
    public String welcome(){
        return "Hello from api!";
    }

    @PostMapping("api/login")
    public ResponseEntity<KorisnikDto> login(@RequestBody LoginDto loginDto, HttpSession session) {
        if (loginDto.getMail().isEmpty() || loginDto.getLozinka().isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        
        String mail = loginDto.getMail();
        List<AutorDto> autori = autorService.findAll();
        for(AutorDto dto : autori){
            if((Objects.equals(dto.getMail(), mail))) {
                if (dto.isAktivnost() == false) {
                    return ResponseEntity.badRequest().body(null);
                }
            }
        }

        Korisnik loggedKorisnik = korisnikService.login(loginDto.getMail(), loginDto.getLozinka(), session);
        if (loggedKorisnik == null) {
            return ResponseEntity.notFound().build();
        }

        KorisnikDto korisnikDto = new KorisnikDto();
        korisnikDto.setId(loggedKorisnik.getId());
        korisnikDto.setIme(loggedKorisnik.getIme());
        korisnikDto.setPrezime(loggedKorisnik.getPrezime());
        korisnikDto.setKorisnickoIme(loggedKorisnik.getKorisnickoIme());
        korisnikDto.setDatumRodjenja(loggedKorisnik.getDatumRodjenja());
        korisnikDto.setProfilnaSlika(loggedKorisnik.getProfilnaSlika());
        korisnikDto.setOpis(loggedKorisnik.getOpis());
        korisnikDto.setUloga(loggedKorisnik.getUloga());
        korisnikDto.setPolice(loggedKorisnik.getPolice());

        //session.setAttribute("employee", loggedKorisnik);
        return ResponseEntity.ok(korisnikDto);
    }


    @PostMapping("api/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("employee");

        if (loggedKorisnik == null) {
            return new ResponseEntity<>("Forbidden", HttpStatus.FORBIDDEN);
        }
        session.invalidate();
        return new ResponseEntity<>("Successfully logged out", HttpStatus.OK);
    }




    @PostMapping("api/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterDto registerDto){

        if(korisnikService.existsMail(registerDto.getMail())){
            return new ResponseEntity<>("Mail already used!", HttpStatus.BAD_REQUEST);
        }
        if(korisnikService.existsKorisnickoIme(registerDto.getKorisnickoIme())){
            return new ResponseEntity<>("Username already used!", HttpStatus.BAD_REQUEST);
        }
        if(korisnikService.existsLozinka(registerDto.getLozinka())){
            return new ResponseEntity<>("Password already used!", HttpStatus.BAD_REQUEST);
        }
        korisnikService.create(registerDto);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }

    @GetMapping("/api/korisnici")
    public ResponseEntity<List<KorisnikDto>> getKorisnici(){
        List<KorisnikDto> dtos = korisnikService.findAll();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/api/korisnici/{id}")
    public ResponseEntity<Korisnik> getKorisnik(@PathVariable Long id){

        Korisnik korisnik = korisnikService.findOne(id);
        return ResponseEntity.ok(korisnik);
    }

    @PutMapping("api/citalac/update-korisnik")
    public ResponseEntity<?> updateUser(@RequestBody UpdateDto updateDto,HttpSession session) {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("employee");
        if (loggedKorisnik.getUloga() == Korisnik.Uloga.CITALAC  || loggedKorisnik.getUloga() == Korisnik.Uloga.AUTOR ) {
            korisnikService.updateUser(loggedKorisnik.getId(), updateDto);
            return new ResponseEntity<>("User updated successfully", HttpStatus.OK);
        }

        return new ResponseEntity<>("Forbidden", HttpStatus.FORBIDDEN);
    }

    @PostMapping("api/zahtev-create")
    public ResponseEntity<?> zahtev(@RequestBody ZahtevDto zahtevDto, HttpSession session) {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("employee");

        if(loggedKorisnik == null){
            if(zahtevZaAktivacijuNalogaAutoraService.create(zahtevDto) == null)
                return new ResponseEntity<>("Autor je aktivan", HttpStatus.BAD_REQUEST);
            return ResponseEntity.ok("Request added");
        }
        else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You are already logged in.");
        }
    }
    @PostMapping("/api/admin/zahtev/{zahtevId}/accept")
    public ResponseEntity<?> zahtevAccept(@PathVariable Long zahtevId, HttpSession session) {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("employee");
        if(loggedKorisnik.getUloga() == Korisnik.Uloga.ADMINISTRATOR){

            ZahtevZaAktivacijuNalogaAutoraDto zanaDto = zahtevZaAktivacijuNalogaAutoraService.findOne(zahtevId);
            String meil = zanaDto.getEmail();
            String meilAutora = zanaDto.getKorisnik().getMail();
            String loznikaAutora = zanaDto.getKorisnik().getLozinka();

            zanaDto.setStatus(ZahtevZaAktivacijuNalogaAutora.Status.odobren);
            zahtevZaAktivacijuNalogaAutoraService.saveDto(zanaDto);

            Long ID = zanaDto.getKorisnik().getId();

            autorService.activateAutor(ID);

            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "localhost");
            properties.put("mail.smtp.port", "25");

            // Email account credentials
            String username = "testtestdsds123@gmail.com";
            String password = "Test!!!123";

            // Email recipient
            //String recipient = "recipient@example.com";
            String recipient = meil;

            // Email content
            String subject = "Mail i lozinka AUTORA";
            String messageContent = "Mail: " + meilAutora + " Loznika: " + loznikaAutora;

            // Create a session with the email server
            Session sessionMail = Session.getInstance(properties, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            try {
                // Create a new email message
                MimeMessage message = new MimeMessage(sessionMail);
                message.setFrom(new InternetAddress(username));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
                message.setSubject(subject);
                message.setText(messageContent);

                // Send the email
                Transport.send(message);

                return new ResponseEntity<>("Email sent", HttpStatus.OK);
            } catch (MessagingException e) {
                e.printStackTrace();
                return new ResponseEntity<>("Failed to send email", HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }else {
            return new ResponseEntity<>("You are not administrator", HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/api/admin/zahtev/{zahtevId}/decline")
    public ResponseEntity<?> zahtevDecline(@PathVariable Long zahtevId, HttpSession session) {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("employee");
        if(loggedKorisnik.getUloga() == Korisnik.Uloga.ADMINISTRATOR){

            ZahtevZaAktivacijuNalogaAutoraDto zanaDto = zahtevZaAktivacijuNalogaAutoraService.findOne(zahtevId);
            String meil = zanaDto.getEmail();

            zanaDto.setStatus(ZahtevZaAktivacijuNalogaAutora.Status.odbijen);
            zahtevZaAktivacijuNalogaAutoraService.saveDto(zanaDto);

            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "localhost");
            properties.put("mail.smtp.port", "25");

            // Email account credentials
            String username = "testtestdsds123@gmail.com";
            String password = "Test!!!123";

            // Email recipient
            //String recipient = "recipient@example.com";
            String recipient = meil;

            // Email content
            String subject = "Obavestenje";
            String messageContent = "Zao nam je, vas zahtev je odbijen";

            // Create a session with the email server
            Session sessionMail = Session.getInstance(properties, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            try {
                // Create a new email message
                MimeMessage message = new MimeMessage(sessionMail);
                message.setFrom(new InternetAddress(username));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
                message.setSubject(subject);
                message.setText(messageContent);

                // Send the email
                Transport.send(message);

                return new ResponseEntity<>("Email sent", HttpStatus.OK);
            } catch (MessagingException e) {
                e.printStackTrace();
                return new ResponseEntity<>("Failed to send email", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else {
            return new ResponseEntity<>("You are not administrator", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("api/zahtev-getAll")
    public ResponseEntity<?> zahtevGet(HttpSession session) {
        List<ZahtevZaAktivacijuNalogaAutoraDto> dtos = zahtevZaAktivacijuNalogaAutoraService.findAll();
        return ResponseEntity.ok(dtos);
    }
    @PostMapping("/api/admin/zanr-add")
    public ResponseEntity<?> addZanr(@RequestBody ZanrAddDto zanrAddDto, HttpSession session) {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("employee");
        if(loggedKorisnik.getUloga() == Korisnik.Uloga.ADMINISTRATOR){
            zanrService.create(zanrAddDto);
            return new ResponseEntity<>("Zanr added seccessfully", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("You are not administrator", HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/api/admin/knjiga-add")
    public ResponseEntity<?> addKnjiga(@RequestBody KnjigaAutorDto knjigaAutorDto, HttpSession session) {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("employee");
        if(loggedKorisnik.getUloga() == Korisnik.Uloga.ADMINISTRATOR){
            knjigaService.createKnjigaAdmin(knjigaAutorDto);
            return new ResponseEntity<>("Book added successfully", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("You are not administrator", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("api/admin/knjiga/{knjigaId}/update_knjiga")
    public ResponseEntity<?> updateKnjigaAdmin(@RequestBody UpdateKnjigaDto updateKnjigaDto,@PathVariable Long knjigaId, HttpSession session) throws ChangeSetPersister.NotFoundException  {
        knjigaService.updateKnjigaAdmin(knjigaId, updateKnjigaDto);
        return new ResponseEntity<>("Book updated successfully", HttpStatus.OK);

    }

    @PutMapping("/api/admin/update_autor/{autorId}")
    public ResponseEntity<?> updateAutor(@RequestBody UpdateDto updateDto, @PathVariable Long autorId, HttpSession session) throws ChangeSetPersister.NotFoundException {
            Autor autor = autorService.findOne(autorId);
            if(!autor.getAktivnost()){
                autorService.updateAutor(autorId, updateDto);
                return new ResponseEntity<>("Autor is updated", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Autor is active", HttpStatus.BAD_REQUEST);
            }
    }
    @PostMapping("/api/citalac/polica/{policaId}/knjiga/{knjigaId}/knjiga-add-polica")
    public ResponseEntity<?> addKnjigaPolica(@PathVariable Long knjigaId,@PathVariable Long policaId,RecenzijaDto recenzijaDto, HttpSession session) throws ChangeSetPersister.NotFoundException {
        Korisnik loggedKorisnik = (Korisnik) session.getAttribute("employee");

        if (loggedKorisnik.getUloga() == Korisnik.Uloga.CITALAC  || loggedKorisnik.getUloga() == Korisnik.Uloga.AUTOR ) {
            Polica polica = policaService.findOneById(policaId);

            if(polica.isPrimarna()){ // Polica je primarna
                if(knjigaService.findKnjigaOnPrimarnaPolica(loggedKorisnik.getId(), knjigaId)) {
                    return new ResponseEntity<>("Knjiga vec postoji na nekoj od primarnih polica", HttpStatus.BAD_REQUEST);
                } else{
                    // Ovde moras paziti da li je stavlja na READ policu
                    if(polica.getNaziv() == "Read"){
                        policaService.addKnjigaOnPolica(policaId, knjigaId);
                        return new ResponseEntity<>("Knjiga je dodata na 'Read' policu", HttpStatus.OK);
                    } else {
                        policaService.addKnjigaOnPolica(policaId, knjigaId);
                        return new ResponseEntity<>("Knjiga je dodata na primarnu policu", HttpStatus.OK);
                    }
                }
            } else { // Polica nije primarna
                if(knjigaService.findKnjigaOnPrimarnaPolica(loggedKorisnik.getId(), knjigaId)){
                    policaService.addKnjigaOnPolica(policaId, knjigaId);
                    return new ResponseEntity<>("Knjiga je dodata na policu koja nije primarna", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Moras je prvo staviti na neku od primarnih polica", HttpStatus.BAD_REQUEST);
                }
            }

        }
        return new ResponseEntity<>("Vi niste taj citalac", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("api/search-users/{string}")
    public ResponseEntity<?> searchUser(@PathVariable String string) {
        List<KorisnikDto> dtos = korisnikService.searchUsers(string);
        if (dtos.isEmpty()) {
            return ResponseEntity.badRequest().body("Ne postoji");
        } else {
            return ResponseEntity.ok(dtos);
        }
    }

}
