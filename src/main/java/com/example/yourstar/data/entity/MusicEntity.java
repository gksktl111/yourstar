package com.example.yourstar.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "music")
public class MusicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "music_id")
    private Long musicId;

    @Column(name = "music_title")
    private String musicTitle;

    @Lob
    @Column(name = "sound_source",columnDefinition = "BLOB")
    byte[] soundSource;

    @Column(name = "lyrics")
    private String lyrics;

    @Column(name = "hits")
    private int hits;
}
