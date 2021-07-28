package com.finalpjc.ott.service;

import com.finalpjc.ott.dto.UserProfileRequestDto;
import com.finalpjc.ott.model.UserProfile;
import com.finalpjc.ott.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;

    @Transactional
    public void postPicture(UserProfileRequestDto requestDto) {
        UserProfile userProfile = new UserProfile(requestDto.getPicture());
        userProfileRepository.save(userProfile);
    }

    @Transactional
    public void postCover(UserProfileRequestDto requestDto) {
        UserProfile userProfile = new UserProfile(requestDto.getCover());
        userProfileRepository.save(userProfile);
    }

    public Map<String, List<String>> getPictureList(String username) {
        Map<String, List<String>> pictureListMap = new HashMap<>();
        List<String> pictureList = new ArrayList<>();
        userProfileRepository.findAllByUsername(username);
        pictureListMap.put("pictures", pictureList);
        return pictureListMap;
    }

    public Map<String, List<String>> getCoverList(String username) {
        Map<String, List<String>> coverListMap = new HashMap<>();
        List<String> coverList = new ArrayList<>();
        userProfileRepository.findAllByUsername(username);
        coverListMap.put("covers", coverList);
        return coverListMap;
    }

    @Transactional
    public void updatePicture(UserProfileRequestDto requestDto) {
        UserProfile userProfile = userProfileRepository.findByUsername(requestDto.getUsername()).orElseThrow(
                () -> new NullPointerException("등록되지 않은 username")

        );
        userProfile.pictureUpdate(requestDto.getPicture());
    }

    @Transactional
    public void updateCover(UserProfileRequestDto requestDto) {
        UserProfile userProfile = userProfileRepository.findByUsername(requestDto.getCover()).orElseThrow(
                () -> new NullPointerException("등록되지 않은 username")
        );
        userProfile.coverUpdate(requestDto.getCover());
    }

    @Transactional
    public void deletePiciture(String username) {
        UserProfile userProfile = userProfileRepository.findByUsername(username).orElseThrow(
                () -> new NullPointerException("등록되지 않은 username")
        );
        userProfile.pictureUpdate("");
    }

    @Transactional
    public void deleteCover(String username) {
        UserProfile userProfile = userProfileRepository.findByUsername(username).orElseThrow(
                () -> new NullPointerException("등록되지 않은 username")
        );
        userProfile.coverUpdate("");
    }


}
