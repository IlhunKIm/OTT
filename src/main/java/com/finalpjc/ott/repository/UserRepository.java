package com.finalpjc.ott.repository;

import com.finalpjc.ott.model.User;
import com.finalpjc.ott.repository.mapping.UsernameMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmailAddress(String emailAddress);
    Long countAllByUsernameContaining(String username);
    Optional<User> findByKakaoId(String kakaoId);
    Optional<Object> findTopByUsername(String username);
    Optional<UsernameMapping> findFirstByUsernameContaining(String username);


}
