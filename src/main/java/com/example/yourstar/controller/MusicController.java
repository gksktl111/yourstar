package com.example.yourstar.controller;

import com.example.yourstar.data.dto.music.*;
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

    // 앨범 저장 album_info
    @PostMapping("/save_album_info")
    public String saveAlbumInfo(Authentication authentication, @ModelAttribute SaveAlbumInfoDto saveAlbumInfoDto) {
        return musicService.saveAlbumInfo(saveAlbumInfoDto);
    }

    // 노래 저장 + 앨범에 추가 music + album
    @PostMapping("/save_music")
    public String saveMusic(Authentication authentication, @RequestBody SaveMusicDto saveMusicDto) {
        return musicService.saveMusic(saveMusicDto);
    }

    // 앨범 리스트 불러오기
    @PostMapping("/get_album_list")
    public List<GetAlbumListDto> getAlbumList(Authentication authentication) {
        try {
            return musicService.getAlbumList();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    // 앨범 불러오기 album_ifo + album
    @PostMapping("/get_album")
    public GetAlbumDto getAlbum(Authentication authentication, @RequestBody AlbumIdDto albumIdDto) {
        try {
            return musicService.getAlbum(albumIdDto.getAlbumId());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    // 노래 불러오기 music + album + album_info
    @PostMapping("/get_music")
    public GetMusicDto getMusic(Authentication authentication, @RequestBody MusicIdDto musicIdDto) {
        try {
            return musicService.getMusic(musicIdDto.getMusicId());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
