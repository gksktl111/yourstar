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
@Table(name = "album_info")
public class AlbumInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "album_info_id")
    private Long albumInfoId;

    @Column(name = "album_title")
    private String albumTitle;

    @Lob
    @Column(name = "album_image",columnDefinition = "BLOB")
    byte[] albumImage;

    @Column(name = "album_release_date")
    private Timestamp albumReleaseDate;

    @Column(name = "album_singer")
    private String albumSinger;
}
