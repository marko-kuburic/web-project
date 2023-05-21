package acs.uns.ac.rs.webproject.repository;

import acs.uns.ac.rs.webproject.entity.AccountActivationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountActivationRequestRepository extends JpaRepository <AccountActivationRequest, Long> {


    List<AccountActivationRequest> findAllByMail(String mail);

    List<AccountActivationRequest> findAllByPhoneNumber(String mail);

    AccountActivationRequest findById(long id);
}