package com.finalpjc.ott.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class UserProfile extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = true)
    private String picture;

    @Column(nullable = true)
    private String cover;

    public UserProfile(String username) {
        this.username = username;
        this.picture = "";
        this.cover = "";
    }


    public void pictureUpdate(String picture) {

        this.picture = picture;
    }

    public void coverUpdate(String cover) {
        this.cover = cover;
    }


}
