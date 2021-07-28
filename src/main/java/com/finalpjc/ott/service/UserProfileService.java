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


    public Map<String, List<String>> getPictureList(String username) {
        Map<String, List<String>> pictureListMap = new HashMap<>();
        List<String> pictureList = new ArrayList<>();
        userProfileRepository.findAllByUsername(username);
        pictureListMap.put("pictures", pictureList);
        return pictureListMap;
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
