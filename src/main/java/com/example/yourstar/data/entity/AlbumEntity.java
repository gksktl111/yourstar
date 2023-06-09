package com.example.yourstar.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "album")
public class AlbumEntity {
    @Id
    @Column(name = "album_id")
    private long albumId;

    @ManyToOne
    @JoinColumn(name = "album_info_id")
    private AlbumInfoEntity albumInfoEntity;

    @ManyToOne
    @JoinColumn(name = "music_id")
    private MusicEntity musicEntity;

    @Column(name = "music_index")
    private int musicIndex;
}
