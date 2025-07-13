package sist.recla.complaintsSystem.controler;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import sist.recla.complaintsSystem.entity.Complaint;
import sist.recla.complaintsSystem.service.ComplaintService;

@RestController
@RequestMapping("/complaint")
public class ComplaintController {

  private final ComplaintService complaintService;

  public ComplaintController(ComplaintService complaintService) {
    this.complaintService = complaintService;
  }

  @PostMapping
  public ResponseEntity<Void> createComplaint(@RequestBody @Valid CreateComplaintDto dto) {
    var complaint = complaintService.createComplaint(dto);
    return ResponseEntity.created(URI.create("/complaint" + complaint)).build();
  }

  @GetMapping
  public ResponseEntity<List<Complaint>> listComplaint() {
    var complaint = complaintService.listComplaints();

    return ResponseEntity.ok(complaint);
  }

}
