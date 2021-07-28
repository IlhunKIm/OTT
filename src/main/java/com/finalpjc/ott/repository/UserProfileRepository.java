package com.finalpjc.ott.repository;

import com.finalpjc.ott.model.UserProfile;
import com.finalpjc.ott.repository.mapping.FriendObjectMappingFromUserProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    Optional<UserProfile> findByUsername(String username);

    FriendObjectMappingFromUserProfile getByUsername(String username);
    List<UserProfile> findAllByUsername(String username);
    List<FriendObjectMappingFromUserProfile> findAllByOrderByModifiedAtDesc();
    Page<FriendObjectMappingFromUserProfile> findAllByOrderByCreatedAtDesc(Pageable pageable);
    List<UserProfile> findAll();
    List<FriendObjectMappingFromUserProfile> findAllByUsernameContaining(String username);


}
