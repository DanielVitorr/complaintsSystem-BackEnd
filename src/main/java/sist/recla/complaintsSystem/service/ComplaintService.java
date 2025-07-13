package sist.recla.complaintsSystem.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import sist.recla.complaintsSystem.controler.CreateComplaintDto;
import sist.recla.complaintsSystem.entity.Complaint;
import sist.recla.complaintsSystem.entity.User;
import sist.recla.complaintsSystem.repository.ComplaintRepository;

@Service
public class ComplaintService {

  private final ComplaintRepository complaintRepository;
  private final UserService userService; // pegar o usuário autenticado

  public ComplaintService(ComplaintRepository complaintRepository, UserService userService) {
    this.complaintRepository = complaintRepository;
    this.userService = userService;
  }

  public UUID createComplaint(CreateComplaintDto dto) {
    
    User user = userService.getAuthenticatedUser();

    var complaint = new Complaint();
    complaint.setTitle(dto.title());
    complaint.setDescription(dto.description());
    complaint.setUser(user);

    var saved = complaintRepository.save(complaint);
    return saved.getId();
  }

  public List<Complaint> listComplaints() {
    return complaintRepository.findAll();
  }

}
