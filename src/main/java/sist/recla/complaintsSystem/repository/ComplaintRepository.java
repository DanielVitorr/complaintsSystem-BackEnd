package sist.recla.complaintsSystem.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import sist.recla.complaintsSystem.entity.Complaint;
import sist.recla.complaintsSystem.entity.User;

public interface ComplaintRepository extends JpaRepository<Complaint, UUID>{

  List<Complaint> findByUser(User user);
  
}
