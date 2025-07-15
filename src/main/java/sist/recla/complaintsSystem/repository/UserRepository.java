package sist.recla.complaintsSystem.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sist.recla.complaintsSystem.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>{

  Optional<User> findByCpf(String cpf);

}
