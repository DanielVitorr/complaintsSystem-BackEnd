package sist.recla.complaintsSystem.entity;

import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_users")
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID userId;

  @Column(name = "CPF", unique = true)
  private String CPF;

  @Column(name = "username")
  private String username;

  @Column(name = "password")
  private String password;

  @CreationTimestamp
  private Instant creationTimestamp;

  @UpdateTimestamp
  private Instant updateTimestamp;

  public User() {

  }

  public User(
      UUID userId, 
      String CPF, 
      String username, 
      String password, 
      Instant creationTimestamp, 
      Instant updateTimestamp
    ) {
    this.userId = userId;
    this.CPF = CPF;
    this.username = username;
    this.password = password;
    this.creationTimestamp = creationTimestamp;
    this.updateTimestamp = updateTimestamp;
  }

  public UUID getUserId() {
    return userId;
  }

  public void setUserId(UUID userId) {
    this.userId = userId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getCPF() {
    return CPF;
  }

  public void setCPF(String CPF) {
    this.CPF = CPF;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Instant getCreationTimestamp() {
    return creationTimestamp;
  }

  public void setCreationTimestamp(Instant creationTimestamp) {
    this.creationTimestamp = creationTimestamp;
  }

  public Instant getUpdateTimestamp() {
    return updateTimestamp;
  }

  public void setUpdateTimestamp(Instant updateTimestamp) {
    this.updateTimestamp = updateTimestamp;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    // Pode personalizar permissões aqui, por enquanto retorna ROLE_USER fixo
    return List.of(new SimpleGrantedAuthority("ROLE_USER"));
  }

  @Override
  public boolean isAccountNonExpired() {
    return true; // considerar conta sempre válida
  }

  @Override
  public boolean isAccountNonLocked() {
    return true; // conta não bloqueada
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true; // credenciais válidas
  }

  @Override
  public boolean isEnabled() {
    return true; // conta habilitada
  }
}
