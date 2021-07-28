package com.finalpjc.ott.controller;

import com.finalpjc.ott.dto.UserProfileRequestDto;
import com.finalpjc.ott.model.UserProfile;
import com.finalpjc.ott.repository.UserProfileRepository;
import com.finalpjc.ott.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UserProfileController {

    private final UserProfileService userProfileService;
    private final UserProfileRepository userProfileRepository;


    @PostMapping("/user/userprofile/picture") // 프로필 사진 올리기
    public void postPicture(UserProfileRequestDto requestDto) {
        userProfileService.postPicture(requestDto);
    }

    @PostMapping("/user/userprofile/cover") // 커버 올리기
    public void postCover(UserProfileRequestDto requestDto) {
        userProfileService.postCover(requestDto);
    }


    @GetMapping("/user/userprofile/pictureList/{username}") // 전체 프로필 사진 리스트 보기
    public Map<String, List<String>> getPictureList(@PathVariable String username) {
        return userProfileService.getPictureList(username);
    }

    @GetMapping("/user/userprofile/coverList/{username}") // 전체 커버 리스트 보기
    public Map<String, List<String>> getCoverList(@PathVariable String username) {
        return userProfileService.getCoverList(username);
    }


    @GetMapping("/user/userprofile/picture/{username}")  // 프로필 사진 조회
    public String getPicture(@PathVariable String username) {
        UserProfile userProfile = userProfileRepository.findByUsername(username).orElseThrow(
                () -> new NullPointerException("등록되지 않은 username")
        );
        return userProfile.getPicture();
    }

    @GetMapping("/user/userprofile/picture/{username}") // 커버 조회
    public String getCover(@PathVariable String username) {
        UserProfile userProfile = userProfileRepository.findByUsername(username).orElseThrow(
                () -> new NullPointerException("등록되지 않은 username")
        );
        return userProfile.getCover();
    }

    @PutMapping("/user/userprofile/picture") // 프로필 사진 수정
    public void updatePicture(@RequestBody UserProfileRequestDto requestDto) {
        userProfileService.updatePicture(requestDto);
    }

    @PutMapping("/user/userprofile/cover") // 커버 수정
    public void updateCover(@RequestBody UserProfileRequestDto requestDto) {
        userProfileService.updateCover(requestDto);
    }

    @DeleteMapping("/user/userprofile/picture/{username}") // 프로필 사진 삭제
    public void deletePicture(@PathVariable String username) {
        userProfileService.deletePiciture(username);
    }

    @DeleteMapping("/user/userprofile/cover/{username}") // 커버 삭제
    public void deleteCover(@PathVariable String username) {
        userProfileService.deleteCover(username);
    }


}
