package sist.recla.complaintsSystem.controler;

import jakarta.validation.constraints.NotBlank;

public record CreateUserDto(

    @NotBlank
    String username, 

    @NotBlank
    String cpf,
    
    @NotBlank
    String password
  ) {}

