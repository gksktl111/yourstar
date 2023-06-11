package com.example.yourstar.service;

import com.example.yourstar.data.dto.music.*;

import java.util.List;

public interface MusicService {
    String saveAlbumInfo(SaveAlbumInfoDto saveAlbumInfoDto); // album_info save
    String saveAlbumMusic(SaveMusicDto saveMusicDto); // music + album save
    List<GetAlbumListDto> getAlbumList(); // 앨범 목록 불러오기
    GetAlbumDto getAlbum(long albumId); // 앨범 정보 불러오기
    GetMusicDto getMusic(long musicId); // 음악 정보 불러오기


    String savePlayListInfo(String userId,SavePlayListInfoDto savePlayListInfoDto); // playList_info save
    List<GetMyPlayListDto> getMyPlayList(String UserId); // 내 플레이 리스트 목록 불러오기
    String saveAPlayListMusic(SavePlayListDto savePlayListDto); // 내 플레이 리스트 노래 추가하기
    String deletePlayListMusic(DeletePLMusicDto deletePLMusicDto); // 내 플레이 리스트 노래 지우기
    GetPLMusicDto getPlayListMusic(long playListId);
    List<GetMusicRankDto> getMusicRank(int page);

}
