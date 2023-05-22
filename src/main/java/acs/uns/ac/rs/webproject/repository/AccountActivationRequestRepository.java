package acs.uns.ac.rs.webproject.repository;

import acs.uns.ac.rs.webproject.entity.AccountActivationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountActivationRequestRepository extends JpaRepository <AccountActivationRequest, Long> {


    List<AccountActivationRequest> findAllByMail(String mail);

    List<AccountActivationRequest> findAllByPhoneNumber(String mail);

    AccountActivationRequest findById(long id);
}