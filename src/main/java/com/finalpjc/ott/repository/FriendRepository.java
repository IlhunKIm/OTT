package com.finalpjc.ott.repository;

import com.finalpjc.ott.model.Friend;
import com.finalpjc.ott.repository.mapping.FriendMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Long> {

    List<Friend> findAllByUsernameAndFriendName(String username, String friendName);
    void deleteAllByUsernameAndFriendName(String username, String friendName);
    List<FriendMapping> findAllByUsername(String username);


}
