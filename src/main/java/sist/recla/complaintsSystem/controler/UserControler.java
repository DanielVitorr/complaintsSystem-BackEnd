package sist.recla.complaintsSystem.controler;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

import sist.recla.complaintsSystem.entity.User;
import sist.recla.complaintsSystem.service.UserService;

@RestController
@RequestMapping("/users")
public class UserControler {

  private UserService userService;

  public UserControler(UserService userService) {
    this.userService = userService;
  }

  // @PostMapping
  // public ResponseEntity<User> createUser(@RequestBody CreateUserDto
  // createUserDto) {
  // var userId = userService.createUser(createUserDto);
  // return ResponseEntity.created(URI.create("/users/" + userId)).build();
  // }

  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody CreateUserDto createUserDto) {
    var userId = userService.createUser(createUserDto);
    var user = userService.getUserById(userId.toString()).orElse(null);
    return ResponseEntity.created(URI.create("/users/" + userId)).body(user);
  }

  @GetMapping("/{userId}")
  public ResponseEntity<User> getUserById(@PathVariable("userId") String userId) {
    var user = userService.getUserById(userId);
    if (user.isPresent()) {
      return ResponseEntity.ok(user.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping
  public ResponseEntity<List<User>> listUsers() {
    var users = userService.listUsers();
    return ResponseEntity.ok(users);
  }

  @GetMapping("/me")
  public ResponseEntity<User> getCurrentUser(Authentication authentication) {
    String cpf = authentication.getName();
    return userService.findByCpf(cpf)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }
}
