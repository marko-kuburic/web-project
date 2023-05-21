package acs.uns.ac.rs.webproject.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import acs.uns.ac.rs.webproject.entity.AccountActivationRequest;
import acs.uns.ac.rs.webproject.service.AccountActivationRequestService;

import java.util.List;

@RestController
public class  AccountActivationRequestController {
    @Autowired
    private AccountActivationRequestService accountActivationRequestService;


    @GetMapping("/api/accountActivationRequests")
    public List<AccountActivationRequest> getAccountActivationRequests(){
        List<AccountActivationRequest> accountActivationRequestList = accountActivationRequestService.findAll();
        return accountActivationRequestList;
    }

    @GetMapping("/api/accountActivationRequests/{id}")
    public AccountActivationRequest getAccountActivationRequest(@PathVariable(name = "id") Long id){
        AccountActivationRequest accountActivationRequest = accountActivationRequestService.findOne(id);
        return accountActivationRequest;
    }

    @GetMapping("/api/accountActivationRequests/search/{mail}")
    public List<AccountActivationRequest> getAllByMail(@PathVariable("mail") String mail){
        List<AccountActivationRequest> accountActivationRequestList = accountActivationRequestService.findAllByMail(mail);
        return accountActivationRequestList;
    }

    @GetMapping("/api/accountActivationRequests/search/{phoneNumber}")
    public List<AccountActivationRequest> getAllByPhoneNumber(@PathVariable("phoneNumber") String phoneNumber){
        List<AccountActivationRequest> accountActivationRequestList = accountActivationRequestService.findAllByPhoneNumber(phoneNumber);
        return accountActivationRequestList;
    }

    @PostMapping("/api/save-accountActivationRequest")
    public String saveAccountActivationRequest(@RequestBody AccountActivationRequest accountActivationRequest) {
        this.accountActivationRequestService.save(accountActivationRequest);
        return "Successfully saved an accountActivationRequest!";
    }

    @PutMapping("api/update-accountActivationRequest")
    public String updateAccountActivationRequest(@RequestBody AccountActivationRequest accountActivationRequest) {
        this.accountActivationRequestService.save(accountActivationRequest);
        return "Successfully saved an accountActivationRequest!";
    }

}