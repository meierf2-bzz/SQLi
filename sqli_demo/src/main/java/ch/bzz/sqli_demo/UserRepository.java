package ch.bzz.sqli_demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByUsernameAndPassword(String username, String password);
}
