package com.example.yourstar.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class UserEntity {

    @Id
    @Column(name = "user_id")
    String userId;

    @Column(name = "user_name")
    String user_Name;

    @Column(name = "user_email")
    String userEmail;

    @Column(name = "user_pw")
    String userPw;

    @Column(name = "user_gender")
    String userGender;

    @Column(name = "user_age")
    int userAge;

    @Column(name = "post_count")
    int postCount;

    @Column(name = "phone")
    String phone;

    @Column(name = "introduce")
    String introduce;

    @Column(name = "join_date")
    Timestamp joinDate;
}
