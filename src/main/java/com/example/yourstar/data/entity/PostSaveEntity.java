package com.example.yourstar.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "post_save")
public class PostSaveEntity {
    @Id
    @Column(name = "post_save_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postSaveId;

    @Column(name = "save_time")
    private Timestamp saveTime;

    @ManyToOne
    @JoinColumn(name = "post_save_userid")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "post_save_postid")
    private PostEntity postEntity;
}
