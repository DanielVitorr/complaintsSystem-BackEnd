package sist.recla.complaintsSystem.controler;

import java.time.Instant;

import jakarta.validation.constraints.NotBlank;

public record CreateComplaintDto(
  
    @NotBlank(message = "Título é obrigatório")
    String title,

    @NotBlank(message = "Descrição é obrigatória")
    String description,

    Instant createdAt
  ) {}