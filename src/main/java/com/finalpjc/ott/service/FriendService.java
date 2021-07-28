package com.finalpjc.ott.service;

import com.finalpjc.ott.dto.FriendRequestRequestDto;
import com.finalpjc.ott.model.Friend;
import com.finalpjc.ott.model.FriendRequest;
import com.finalpjc.ott.repository.FriendRepository;
import com.finalpjc.ott.repository.FriendRequestRepository;
import com.finalpjc.ott.repository.UserProfileRepository;
import com.finalpjc.ott.repository.UserRepository;
import com.finalpjc.ott.repository.mapping.FriendMapping;
import com.finalpjc.ott.repository.mapping.FriendObjectMappingFromUserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@RequiredArgsConstructor
@Service
public class FriendService {

    private final FriendRepository friendRepository;
    private final FriendRequestRepository friendRequestRepository;
    private final UserProfileRepository userProfileRepository;
    private final UserRepository userRepository;

    @Transactional
    public Map<String, String> requestFriend(FriendRequestRequestDto requestDto) {
        String username = requestDto.getUsername();
        String friendName = requestDto.getFriendName();

        if (username.equals(friendName)) {
            throw new IllegalArgumentException("자신에게 친구 신청을 할 수 없습니다.");
        }
        Map<String, String> requestFriendMsgMap = new HashMap<>();
        if (userRepository.findByUsername(username).isPresent()) {
            if (friendRequestRepository.findAllByUsernameAndFriendName(username, friendName).isEmpty()) {
                friendRequestRepository.save(new FriendRequest(username, friendName));
                requestFriendMsgMap.put("code", "200");
                requestFriendMsgMap.put(
                        "msg", "friend request " + username + " to " + friendName + " has been complted."
                );
            } else {
                requestFriendMsgMap.put("code", "209");
                requestFriendMsgMap.put(
                        "msg", "friend request " + username + " to " + friendName + " is already in progress."
                );
            }
        } else {
            requestFriendMsgMap.put("code", "500");
            requestFriendMsgMap.put("msg", "unregistered user.");
        }
        return requestFriendMsgMap;
    }


    public Map<String, Boolean> requestFriendChecker(String username, String friendName) {
        Map<String, Boolean> requestFriendCheckerMap = new HashMap<>();
        requestFriendCheckerMap.put("requestChecker", !friendRequestRepository.findAllByUsernameAndFriendName(username, friendName).isEmpty());
        return requestFriendCheckerMap;
    }

    public List<FriendObjectMappingFromUserProfile> getReceivedRequestFriendList(String username) {
        List<FriendObjectMappingFromUserProfile> friendProfileList = new ArrayList<>();
        for (FriendRequest friendRequest : friendRequestRepository.findAllByFriendName(username)) {
            String requestName = friendRequest.getUsername();
            friendProfileList.add(userProfileRepository.getByUsername(requestName));
        }
        return friendProfileList;
    }


    public Map<String, List<FriendObjectMappingFromUserProfile>> getFriendsRecommend(String username) {
        Map<String, List<FriendObjectMappingFromUserProfile>> friendsRecommendListMap = new HashMap<>();
        List<FriendObjectMappingFromUserProfile> friendsRecommendList = new ArrayList<>();
        HashSet<Integer> pageSet = new HashSet<>();

        Random random = new Random();

        long totalUserDataSize = userProfileRepository.count() - 1; // 자신 제외
        final int dataSizeWhenServiceIsSmall = 20;
        final int dataReturnSize = 12; // 리턴할 유저 데이터 수
        final int totalPages = 4; // 몇 페이지로?
        final int sizePerPage = 4; // totalPages * sizePerPage > 12

        if (totalUserDataSize <= dataSizeWhenServiceIsSmall) {
            List<FriendObjectMappingFromUserProfile> tempUserList;
            tempUserList = userProfileRepository.findAllByOrderByModifiedAtDesc();
            Collections.shuffle(tempUserList);
            for (FriendObjectMappingFromUserProfile userProfile : tempUserList) {
                if (!username.equals(userProfile.getUsername())) {
                    friendsRecommendList.add(userProfile);
                }
                if (friendsRecommendList.size() == dataReturnSize) {
                    break;
                }
            }
            friendsRecommendListMap.put("recommendFriends", friendsRecommendList);
            return friendsRecommendListMap;
        } else {
            while ((pageSet.size() <= totalPages)) {
                pageSet.add(random.nextInt((int) (totalUserDataSize / sizePerPage)));
            }
        }
        for (int i = 0; i < pageSet.size(); i++) {
            Pageable pageable = PageRequest.of(random.nextInt(), sizePerPage);
            userProfileRepository.findAllByOrderByCreatedAtDesc(pageable).forEach(friendsRecommendList::add);
        }
        Collections.shuffle(friendsRecommendList);

        List<FriendObjectMappingFromUserProfile> finalRecommendList = new ArrayList<>();

        for (FriendObjectMappingFromUserProfile finalUserProfile : finalRecommendList) {
            if (!username.equals(finalUserProfile.getUsername())) {
                finalRecommendList.add(finalUserProfile);
            }
            if (friendsRecommendList.size() == dataReturnSize) {
                break;
            }
        }

        friendsRecommendListMap.put("recommendFriends", finalRecommendList);
        return friendsRecommendListMap;
    }

    public List<FriendObjectMappingFromUserProfile> getGivenRequestFriendList(String username) {
        List<FriendObjectMappingFromUserProfile> friendProfileList = new ArrayList<>();
        for (FriendRequest friendRequest : friendRequestRepository.findAllByUsername(username)) {
            String friendName = friendRequest.getFriendName();
            friendProfileList.add(userProfileRepository.getByUsername(friendName));
        }
        return friendProfileList;

    }


    @Transactional
    public void acceptFriend(FriendRequestRequestDto requestDto) {
        String username = requestDto.getUsername();
        String friendName = requestDto.getFriendName();
        if (friendRequestRepository.findAllByUsernameAndFriendName(friendName, username).isEmpty()) {
            throw new NullPointerException("친구 신청이 없습니다.");
        } else {
            if (friendRequestRepository.findAllByUsernameAndFriendName(username, friendName).isEmpty()) {
                friendRepository.save(new Friend(username, friendName));
                if (friendRepository.findAllByUsernameAndFriendName(friendName, username).isEmpty()) {
                    friendRepository.save(new Friend(friendName, username));
                }

                friendRequestRepository.deleteByUsernameAndFriendName(username, friendName);
                friendRequestRepository.deleteByUsernameAndFriendName(friendName, username);
            }
        }

    }

    @Transactional
    public void deleteFriend(String username, String friendName) {
        if (userRepository.findByUsername(username).isPresent()) {
            if (friendRepository.findAllByUsernameAndFriendName(username, friendName).isEmpty()) {
                throw new IllegalArgumentException(username + "&" + friendName + "은 친구 관계가 아닙니다.");
            } else {
                throw new NullPointerException("User not found.");
            }
        }
    }

    @Transactional
    public void declineReceivedFriend(String username, String friendName) {
        friendRequestRepository.deleteByUsernameAndFriendName(username, friendName);

    }

    @Transactional
    public void declineGivenFriend(String username, String friendName) {
        friendRequestRepository.deleteByUsernameAndFriendName(username, friendName);
    }


    public Map<String, List<FriendObjectMappingFromUserProfile>> myFriendsList(String username) {
        if (userRepository.findByUsername(username).isPresent()) {
            Map<String, List<FriendObjectMappingFromUserProfile>> friendListMap = new HashMap<>();
            List<FriendObjectMappingFromUserProfile> friendObjectList = new ArrayList<>();

            for (FriendMapping name : friendRepository.findAllByUsername(username)) {
                FriendObjectMappingFromUserProfile friend = userProfileRepository.getByUsername(name.getFriendName());
                friendObjectList.add(friend);
            }

            friendListMap.put("friends", friendObjectList);
            return friendListMap;
        } else {
            throw new NullPointerException(username + " -> 없는 username입니다.");


        }
    }


    public List<FriendObjectMappingFromUserProfile> getRecievedRequestFriendList(String username) {
        List<FriendObjectMappingFromUserProfile> friendProfileList = new ArrayList<>();
        for (FriendRequest friendRequest : friendRequestRepository.findAllByFriendName(username)) {
            String requestName = friendRequest.getUsername();
            friendProfileList.add(userProfileRepository.getByUsername(requestName));
        }
        return friendProfileList;

    }


}
