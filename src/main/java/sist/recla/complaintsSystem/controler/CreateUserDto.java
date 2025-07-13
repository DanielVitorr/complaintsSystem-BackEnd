package sist.recla.complaintsSystem.controler;

import jakarta.validation.constraints.NotBlank;

public record CreateUserDto(

    @NotBlank
    String username, 

    @NotBlank
    String CPF,
    
    @NotBlank
    String password
  ) {}

