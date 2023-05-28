package acs.uns.ac.rs.webproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import acs.uns.ac.rs.webproject.entity.AccountActivationRequest;
import acs.uns.ac.rs.webproject.entity.Status;
import acs.uns.ac.rs.webproject.repository.AccountActivationRequestRepository;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.List;
import java.util.Optional;
import java.util.Properties;

@Service
public class AccountActivationRequestService {

    @Autowired
    private AccountActivationRequestRepository accountActivationRequestRepository;

    public AccountActivationRequest findOne(Long id)
    {
        Optional<AccountActivationRequest> foundAccountActivationRequest = accountActivationRequestRepository.findById(id);
        System.out.println(foundAccountActivationRequest.get());
        if (foundAccountActivationRequest.isPresent())
            return foundAccountActivationRequest.get();

        return null;
    }

    public List<AccountActivationRequest> findAllByMail(String mail){return accountActivationRequestRepository.findAllByMail(mail);}

    public List<AccountActivationRequest> findAllByPhoneNumber(String phone){return accountActivationRequestRepository.findAllByPhoneNumber(phone);}
    public List<AccountActivationRequest> findAll(){ return accountActivationRequestRepository.findAll();}

    public AccountActivationRequest save(AccountActivationRequest accountActivationRequest){ return accountActivationRequestRepository.save(accountActivationRequest);}
    
    public void sendMail(AccountActivationRequest acc, Status st){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("webprojekatin@gmail.com");
        mailSender.setPassword("rrtmuihqcejfhnut");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(acc.getMail());
        message.setSubject("Zahtev za profil autora");

        String text = "Vidi druze, zahtev ti je ";

        if(acc.getStatus() == Status.APPROVED)
            text += "prihvacen.";
        else
            text += "odbijen.";

        message.setText(text);

        mailSender.send(message);

    };
}
