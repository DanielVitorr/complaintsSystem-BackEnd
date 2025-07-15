package sist.recla.complaintsSystem.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import sist.recla.complaintsSystem.controler.CreateUserDto;
import sist.recla.complaintsSystem.entity.User;
import sist.recla.complaintsSystem.repository.UserRepository;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserService {

  private UserRepository userRepository;
  private PasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public UUID createUser(CreateUserDto createUserDto) {
    var entity = new User();
    entity.setUsername(createUserDto.username());
    entity.setCpf(createUserDto.cpf());
    entity.setPassword(passwordEncoder.encode(createUserDto.password()));

    var userSaved = userRepository.save(entity);

    return userSaved.getUserId();
  }

  public Optional<User> getUserById(String userId) {
    return userRepository.findById(UUID.fromString(userId));
  }

  public User getAuthenticatedUser() {
    String cpf = SecurityContextHolder.getContext().getAuthentication().getName();

    return userRepository.findByCpf(cpf)
      .orElseThrow(() -> new UsernameNotFoundException("Usuário não cadastrado: " + cpf));
  }

  public Optional<User> findByCpf(String cpf) {
    return userRepository.findByCpf(cpf);
  }


  public List<User> listUsers() {
    return userRepository.findAll();
  }

}
