package acs.uns.ac.rs.webproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import acs.uns.ac.rs.webproject.entity.AccountActivationRequest;
import acs.uns.ac.rs.webproject.entity.Status;
import acs.uns.ac.rs.webproject.repository.AccountActivationRequestRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AccountActivationRequestService {

    @Autowired
    private AccountActivationRequestRepository accountActivationRequestRepository;

    public AccountActivationRequest findOne(Long id)
    {
        Optional<AccountActivationRequest> foundAccountActivationRequest = accountActivationRequestRepository.findById(id);
        if (foundAccountActivationRequest.isPresent())
            return foundAccountActivationRequest.get();

        return null;
    }

    public List<AccountActivationRequest> findAllByMail(String mail){return accountActivationRequestRepository.findAllByMail(mail);}

    public List<AccountActivationRequest> findAllByPhoneNumber(String phone){return accountActivationRequestRepository.findAllByPhoneNumber(phone);}
    public List<AccountActivationRequest> findAll(){ return accountActivationRequestRepository.findAll();}

    public AccountActivationRequest save(AccountActivationRequest accountActivationRequest){ return accountActivationRequestRepository.save(accountActivationRequest);}

    public void updateAccountActivationRequest(long id, Status st){
        accountActivationRequestRepository.findById(id).setStatus(st);
    }
}
