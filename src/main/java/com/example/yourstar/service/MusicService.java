package com.example.yourstar.service;

import com.example.yourstar.data.dto.music.*;

import java.util.List;

public interface MusicService {
    String saveAlbumInfo(SaveAlbumInfoDto saveAlbumInfoDto); // album_info save
    String saveMusic(SaveMusicDto saveMusicDto); // music + album save
    List<GetAlbumListDto> getAlbumList(); // 앨범 목록 리턴
    GetAlbumDto getAlbum(long albumId); // 앨범 정보 리턴
    GetMusicDto getMusic(long musicId); // 음악 정보 리턴
}
