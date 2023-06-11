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
@Table(name = "playlist_info")
public class PlayLIstInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "playlist_info_id")
    private Long playListInfoId;

    @Column(name = "playlist_title")
    private String playListTitle;

    @Lob
    @Column(name = "playlist_image",columnDefinition = "BLOB")
    byte[] playListImage;

    @Column(name = "playlist_make_date")
    private Timestamp playListMakeDate;

    @ManyToOne
    @JoinColumn(name = "playlist_make_user")
    private UserEntity playListMakeUser;
}
