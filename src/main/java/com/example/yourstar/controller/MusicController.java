package com.example.yourstar.controller;

import com.example.yourstar.data.dto.music.*;
import com.example.yourstar.data.dto.profile.PageDto;
import com.example.yourstar.service.MusicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class MusicController {
    private MusicService musicService;
    @Autowired
    public MusicController(MusicService musicService){
        this.musicService = musicService;
    }

    // 앨범 정보 저장 album_info
    @PostMapping(value = "/save_album_info")
    public String saveAlbumInfo(Authentication authentication, @ModelAttribute SaveAlbumInfoDto saveAlbumInfoDto) {
        return musicService.saveAlbumInfo(saveAlbumInfoDto);
    }

    // 노래 저장 + 앨범에 노래 추가
    @PostMapping(value = "/save_album_music")
    public String saveAlbumMusic(Authentication authentication, @RequestBody SaveMusicDto saveMusicDto) {
        return musicService.saveAlbumMusic(saveMusicDto);
    }

    // 앨범 리스트 불러오기
    @PostMapping(value = "/get_album_list")
    public List<GetAlbumListDto> getAlbumList(Authentication authentication) {
        try {
            return musicService.getAlbumList();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    // 앨범 수록곡 불러오기 album_ifo + album
    @PostMapping(value = "/get_album")
    public GetAlbumDto getAlbum(Authentication authentication, @RequestBody AlbumIdDto albumIdDto) {
        try {
            return musicService.getAlbum(albumIdDto.getAlbumId());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    // 노래 정보 불러오기 music + album + album_info
    @PostMapping(value = "/get_music")
    public GetMusicDto getMusic(Authentication authentication, @RequestBody MusicIdDto musicIdDto) {
        try {
            return musicService.getMusic(musicIdDto.getMusicId());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    // 플레이 리스트 만들기
    @PostMapping(value = "/save_playlist_info")
    public String savePlayListInfo(Authentication authentication, @ModelAttribute SavePlayListInfoDto savePlayListInfoDto) {
        return musicService.savePlayListInfo(authentication.getName(),savePlayListInfoDto);
    }

    // 내 플레이 리스트 불러오기
    @PostMapping(value = "/get_my_playlist")
    public List<GetMyPlayListDto> getMyPlayList(Authentication authentication) {
        try {
            return musicService.getMyPlayList(authentication.getName());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    // 내 플레이 리스트 수록곡 가져오기
    @PostMapping(value = "/get_my_playlist_music")
    public GetPLMusicDto getMyPlayListMusic(Authentication authentication, @RequestBody PlayListIdDto playListIdDto) {
        try {
            return musicService.getPlayListMusic(playListIdDto.getPlayListIdDto());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    // 플레이 리스트에 노래 추가하기
    @PostMapping(value = "/save_playlist_music")
    public String savePlayLIstMusic(Authentication authentication, @RequestBody SavePlayListDto savePlayListDto) {
        return musicService.saveAPlayListMusic(savePlayListDto);
    }

    // 플레이 리스트에 노래 삭제하기
    @PostMapping(value = "/delete_playlist_music")
    public String deletePlayLIstMusic(Authentication authentication, @RequestBody DeletePLMusicDto deletePLMusicDto) {
        return musicService.deletePlayListMusic(deletePLMusicDto);
    }

    // 음악 순위 10씩 불러오기
    @PostMapping(value = "/get_music_rank")
    public List<GetMusicRankDto> getMusicRnak(Authentication authentication, PageDto pageDto){
        return musicService.getMusicRank(pageDto.getPage());
    }


}
