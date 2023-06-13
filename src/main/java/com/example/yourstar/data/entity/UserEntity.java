package com.example.yourstar.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @Column(name = "user_id")
    String userId;

    @Column(name = "user_name")
    String userName;

    @Column(name = "user_email")
    String userEmail;

    @Column(name = "user_pw")
    String userPw;

    @Column(name = "user_gender")
    String userGender;

    @Column(name = "user_age")
    int userAge;

    @Column(name = "phone")
    String phone;

    @Column(name = "join_date")
    Timestamp joinDate;

    @OneToOne(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private UserProfileEntity userProfileEntity;

    @OneToMany(mappedBy = "toUserId")
    private List<FollowEntity> followers;
}

