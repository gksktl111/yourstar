package com.example.yourstar.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "playlist")
public class PlayListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "playlist_id")
    private Long playlistId;

    @ManyToOne
    @JoinColumn(name = "playlist_music_id")
    private MusicEntity musicEntity;

    @ManyToOne
    @JoinColumn(name = "playlist_info_id")
    private PlayLIstInfoEntity playLIstInfoEntity;

    @Column(name = "playlist_index")
    private int playListIndex;
}
