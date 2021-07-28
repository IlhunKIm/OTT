package com.finalpjc.ott.repository;

import com.finalpjc.ott.model.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {

    List<FriendRequest> findAllByUsernameAndFriendName(String username, String friendName);
    List<FriendRequest> findAllByFriendName(String friendName);
    List<FriendRequest> findAllByUsername(String username);
    void deleteByUsernameAndFriendName(String username, String friendName);

}
