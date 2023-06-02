package com.example.yourstar.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_profile")
public class UserProfileEntity {

    @Id
    @Column(name = "user_id")
    String userId;

    @Column(name = "introduce")
    String introduce;

    @Lob
    @Column(name = "profile_image",columnDefinition = "BLOB")
    byte[] userProfile;
}
