package com.finalpjc.ott.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id

    private Long id;

    @Column(nullable = false)
    String username;

    @Column(nullable = false)
    String password;

    @Column(nullable = false, unique = true)
    private String emailAddress;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    @Column(nullable = true)
    private Long kakaoId;

    public User(String username, String password, String emailAddress, UserRole role) {
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
        this.role = role;
        this.kakaoId = null;
    }

    public User(String nickname, String encodedPassword, String emailAddress, UserRole role, Long kakaoId) {
        this.username = nickname;
        this.password = encodedPassword;
        this.emailAddress = emailAddress;
        this.role = role;
        this.kakaoId = kakaoId;
    }

}
