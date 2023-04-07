package acs.uns.ac.rs.webproject.repository;

import acs.uns.ac.rs.webproject.entity.AccountActivationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountActivationRequestRepository extends JpaRepository <AccountActivationRequest, Long> {
}