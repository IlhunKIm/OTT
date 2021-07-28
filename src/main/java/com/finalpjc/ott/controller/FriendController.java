package com.finalpjc.ott.controller;

import com.finalpjc.ott.repository.FriendRequestRepository;
import com.finalpjc.ott.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FriendController {

    private final FriendService friendService;
    private final FriendRequestRepository friendRequestRepository;



}
