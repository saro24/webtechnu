package rw.artmuse.gallery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.artmuse.gallery.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);




}

