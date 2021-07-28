package com.finalpjc.ott.controller;

import com.finalpjc.ott.dto.FriendRequestRequestDto;
import com.finalpjc.ott.repository.mapping.FriendObjectMappingFromUserProfile;
import com.finalpjc.ott.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class FriendController {

    private final FriendService friendService;

    // 추천 친구 목록 표시
    @GetMapping("user/friend-recommend/{username}")
    public Map<String, List<FriendObjectMappingFromUserProfile>> getFriendRecommend(@PathVariable String username) {
        return friendService.getFriendsRecommend(username);

    }

    // 친구 신청
    @PostMapping("user/request-friend")
    public Map<String, String> requestFriend(@RequestBody FriendRequestRequestDto friendRequestRequestDto) {
        return friendService.requestFriend(friendRequestRequestDto);
    }

    //친구 신청 여부 확인
    @GetMapping("/user/request-friend/{username}/{friendName}")
    public Map<String, Boolean> requestFriendChecker(@PathVariable String username, @PathVariable String friendName) {
        return friendService.requestFriendChecker(username, friendName);
    }

    // 친구 신청 승낙
    @PostMapping("/user/accept-friend")
    public void acceptFriend(@RequestBody FriendRequestRequestDto friendRequestRequestDto) {
        friendService.acceptFriend(friendRequestRequestDto);
    }

    // 친구 목록
    @GetMapping("/user/friend/{username}")
    public Map<String, List<FriendObjectMappingFromUserProfile>> getMyfriendList(@PathVariable String username) {
        return friendService.myFriendsList(username);
    }

    // 친구 삭제
    @DeleteMapping("/user/delete-friend/{username}/{friendname}")
    public void deleteFriend(@PathVariable String username, @PathVariable String friendname) {
        friendService.deleteFriend(username, friendname);
        friendService.deleteFriend(friendname, username);
    }

    // (신청한 사람 기준) 친구 신청 목록 확인
    @GetMapping("/user/request-friend-list/given/{username}")
    public List<FriendObjectMappingFromUserProfile> getGivenRequestFriendList(@PathVariable String username) {
        return friendService.getGivenRequestFriendList(username);
    }

    // (신청 받은 사람 기준) 친구 신청 목록 확인
    @GetMapping("user/request-friend-list/requested/{username}")
    public List<FriendObjectMappingFromUserProfile> getRecievedRequestFriendList(@PathVariable String username) {
        return friendService.getRecievedRequestFriendList(username);
    }

    // (신청한 사람 기준) 친구 신청 거절
    @DeleteMapping("user/decline-friend/given/{username}/{friendname}")
    public void declineReceivedFriend(@PathVariable String username, @PathVariable String friendname) {
        friendService.declineReceivedFriend(username, friendname);
    }

    // (신청 받은 사람 기준) 친구 신청 거절
    @DeleteMapping("user/decline-friend/received/{username}/{friendname}")
    public void declineGivenFriend(@PathVariable String username, @PathVariable String friendname) {
        friendService.declineGivenFriend(username, friendname);
    }



}
