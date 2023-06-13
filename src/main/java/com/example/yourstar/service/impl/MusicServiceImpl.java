package com.example.yourstar.service.impl;

import com.example.yourstar.data.dto.music.*;
import com.example.yourstar.data.entity.AlbumInfoEntity;
import com.example.yourstar.data.entity.MusicEntity;
import com.example.yourstar.data.entity.PlayLIstInfoEntity;
import com.example.yourstar.data.entity.PlayListEntity;
import com.example.yourstar.data.repository.*;
import com.example.yourstar.service.MusicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MusicServiceImpl implements MusicService {
    private AlbumInfoRepository albumInfoRepository;
    private MusicRepository musicRepository;
    private PlayListRepository playListRepository;
    private PlayListInfoRepository playListInfoRepository;
    private UserRepository userRepository;

    @Autowired
    public  MusicServiceImpl(AlbumInfoRepository albumInfoRepository,MusicRepository musicRepository,PlayListRepository playListRepository,PlayListInfoRepository playListInfoRepository,UserRepository userRepository){
        this.albumInfoRepository = albumInfoRepository;
        this.musicRepository = musicRepository;
        this.playListRepository = playListRepository;
        this.playListInfoRepository = playListInfoRepository;
        this.userRepository = userRepository;
    }

    @Override
    public String saveAlbumInfo(SaveAlbumInfoDto saveAlbumInfoDto) {
        try {
            AlbumInfoEntity albumInfoEntity = new AlbumInfoEntity();
            albumInfoEntity.setAlbumTitle(saveAlbumInfoDto.getTitle());
            albumInfoEntity.setAlbumImage(saveAlbumInfoDto.getAlbumImageByte());
            albumInfoEntity.setAlbumSinger(saveAlbumInfoDto.getSinger());
            albumInfoRepository.save(albumInfoEntity);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "failed";
        }

    }

    @Override
    public String saveAlbumMusic(SaveMusicDto saveMusicDto) {
        try {
            MusicEntity musicEntity = new MusicEntity();

            musicEntity.setMusicTitle(saveMusicDto.getMusicTitle());
            musicEntity.setLyrics(saveMusicDto.getLyrics());
            musicEntity.setSoundSource(saveMusicDto.getSoundSourceByte());
            musicEntity.setAlbumInfoEntity(albumInfoRepository.getById(saveMusicDto.getAlbumInfoId()));

            musicRepository.save(musicEntity);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "failed";
        }
    }

    @Override
    public List<GetAlbumListDto> getAlbumList() {
        List<AlbumInfoEntity> albumInfoEntityList = albumInfoRepository.findAll();

        List<GetAlbumListDto> albumListDto = albumInfoEntityList.stream()
                .map(albumInfoEntity -> {
                    GetAlbumListDto getAlbumListDto = new GetAlbumListDto();
                    getAlbumListDto.setAlbumId(albumInfoEntity.getAlbumInfoId());
                    getAlbumListDto.setAlbumTitle(albumInfoEntity.getAlbumTitle());
                    getAlbumListDto.setAlbumReleaseDate(albumInfoEntity.getAlbumReleaseDate());
                    getAlbumListDto.setSinger(albumInfoEntity.getAlbumSinger());
                    try {
                        getAlbumListDto.setAlbumImage(Base64.getEncoder().encodeToString(albumInfoEntity.getAlbumImage()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return getAlbumListDto;
                })
                .collect(Collectors.toList());
        return albumListDto;
    }

    @Override
    public GetAlbumDto getAlbum(long albumId) {

        AlbumInfoEntity albumInfoEntity = albumInfoRepository.getById(albumId);
        GetAlbumDto getAlbumDto = new GetAlbumDto();
        getAlbumDto.setAlbumTitle(albumInfoEntity.getAlbumTitle());
        getAlbumDto.setSinger(albumInfoEntity.getAlbumSinger());
        getAlbumDto.setAlbumImage(Base64.getEncoder().encodeToString(albumInfoEntity.getAlbumImage()));
        getAlbumDto.setAlbumReleaseDate(albumInfoEntity.getAlbumReleaseDate());

        List<MusicEntity> musicEntityList = musicRepository.findByAlbumInfoEntityOrderByHitsAsc(albumInfoEntity);

        List<MusicIdTitleDto> musicIdTitleDtoList = musicEntityList.stream()
                .map(musicEntity -> {
                    MusicIdTitleDto musicIdTitleDto = new MusicIdTitleDto();
                    musicIdTitleDto.setMusicId(musicEntity.getMusicId());
                    musicIdTitleDto.setMusicTitle(musicEntity.getMusicTitle());
                    musicIdTitleDto.setSinger(albumInfoEntity.getAlbumSinger());
                    musicIdTitleDto.setMusicImage(Base64.getEncoder().encodeToString(albumInfoEntity.getAlbumImage()));
                    return musicIdTitleDto;
                })
                .collect(Collectors.toList());

        getAlbumDto.setMusicInfo(musicIdTitleDtoList);

        return getAlbumDto;
    }

    @Override
    public GetMusicDto getMusic(long musicId) {

        MusicEntity musicEntity = musicRepository.getById(musicId);
        GetMusicDto getMusicDto = new GetMusicDto();

        getMusicDto.setMusicId(musicId);
        getMusicDto.setMusicTitle(musicEntity.getMusicTitle());
        getMusicDto.setSoundSource(Base64.getEncoder().encodeToString(musicEntity.getSoundSource()));
        getMusicDto.setLyrics(musicEntity.getLyrics());

        AlbumInfoEntity albumInfoEntity = albumInfoRepository.getById(musicEntity.getAlbumInfoEntity().getAlbumInfoId());
        getMusicDto.setSinger(albumInfoEntity.getAlbumSinger());
        getMusicDto.setMusicImage(Base64.getEncoder().encodeToString(albumInfoEntity.getAlbumImage()));

        musicEntity.setHits(musicEntity.getHits()+1);
        musicRepository.save(musicEntity);
        return getMusicDto;
    }

    @Override
    public String savePlayListInfo(String userId,SavePlayListInfoDto savePlayListInfoDto) {
        try {
            PlayLIstInfoEntity playLIstInfoEntity = new PlayLIstInfoEntity();
            playLIstInfoEntity.setPlayListTitle(savePlayListInfoDto.getPlayListTitle());
            playLIstInfoEntity.setPlayListImage(savePlayListInfoDto.getPlayListImageByte());
            playLIstInfoEntity.setPlayListMakeDate(new Timestamp(System.currentTimeMillis()));
            playLIstInfoEntity.setPlayListMakeUser(userRepository.getById(userId));
            playListInfoRepository.save(playLIstInfoEntity);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "failed";
        }
    }

    @Override
    public List<GetMyPlayListDto> getMyPlayList(String userId) {
        List<PlayLIstInfoEntity> playLIstInfoEntityList = playListInfoRepository.findByPlayListMakeUserOrderByPlayListMakeDateDesc(userRepository.getById(userId));

        List<GetMyPlayListDto> getMyPlayListDtoList = playLIstInfoEntityList.stream()
                .map(playLIstInfoEntity -> {
                    GetMyPlayListDto getMyPlayListDto = new GetMyPlayListDto();
                    getMyPlayListDto.setPlayListId(playLIstInfoEntity.getPlayListInfoId());
                    getMyPlayListDto.setPlayListTitle(playLIstInfoEntity.getPlayListTitle());
                    getMyPlayListDto.setPlayListMakeDate(playLIstInfoEntity.getPlayListMakeDate());
                    getMyPlayListDto.setPlayListMakeUser(playLIstInfoEntity.getPlayListMakeUser().getUserId());
                    try {
                        getMyPlayListDto.setPlayListImage(Base64.getEncoder().encodeToString(playLIstInfoEntity.getPlayListImage()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return getMyPlayListDto;
                })
                .collect(Collectors.toList());
        return getMyPlayListDtoList;
    }

    @Override
    public String saveAPlayListMusic(SavePlayListDto savePlayListDto) {
        try {
            PlayLIstInfoEntity playLIstInfoEntity = playListInfoRepository.getById(savePlayListDto.getPlayListInfoId());
            MusicEntity musicEntity = musicRepository.getById(savePlayListDto.getMusicId());

            PlayListEntity playListEntity = new PlayListEntity();
            playListEntity.setPlayLIstInfoEntity(playLIstInfoEntity);
            playListEntity.setMusicEntity(musicEntity);
            playListEntity.setPlayListIndex(savePlayListDto.getPlayListIndex());

            playListRepository.save(playListEntity);

            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "failed";
        }
    }

    @Override
    public String deletePlayListMusic(DeletePLMusicDto deletePLMusicDto) {
        try {
            PlayLIstInfoEntity playLIstInfoEntity = playListInfoRepository.getById(deletePLMusicDto.getPlayListId());
            MusicEntity musicEntity = musicRepository.getById(deletePLMusicDto.getMusicId());

            playListRepository.deleteByMusicEntityAndPlayListInfoEntity(musicEntity,playLIstInfoEntity);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "failed";
        }

    }

    @Override
    public GetPLMusicDto getPlayListMusic(long playListId) {
        PlayLIstInfoEntity playLIstInfoEntity = playListInfoRepository.getById(playListId);
        GetPLMusicDto getPLMusicDto = new GetPLMusicDto();
        getPLMusicDto.setPlayListTitle(playLIstInfoEntity.getPlayListTitle());
        getPLMusicDto.setPlayListImage(Base64.getEncoder().encodeToString(playLIstInfoEntity.getPlayListImage()));
        getPLMusicDto.setPlayListMakeUser(playLIstInfoEntity.getPlayListMakeUser().getUserId());
        getPLMusicDto.setPlayListMakeDate(playLIstInfoEntity.getPlayListMakeDate());

        List<PlayListEntity> playListEntityList = playListRepository.findByPlayLIstInfoEntityOrderByPlayListIndexAsc(playLIstInfoEntity);

        List<MusicIdTitleDto> musicIdTitleDtoList = playListEntityList.stream()
                .map(playListEntity -> {
                    MusicIdTitleDto musicIdTitleDto = new MusicIdTitleDto();
                    musicIdTitleDto.setMusicId(playListEntity.getMusicEntity().getMusicId());
                    musicIdTitleDto.setMusicTitle(playListEntity.getMusicEntity().getMusicTitle());
                    musicIdTitleDto.setSinger(playListEntity.getMusicEntity().getAlbumInfoEntity().getAlbumSinger());
                    musicIdTitleDto.setMusicImage(Base64.getEncoder().encodeToString(playListEntity.getMusicEntity().getAlbumInfoEntity().getAlbumImage()));
                    return musicIdTitleDto;
                })
                .collect(Collectors.toList());

        getPLMusicDto.setMusicInfo(musicIdTitleDtoList);

        return getPLMusicDto;
    }

    @Override
    public List<GetMusicRankDto> getMusicRank(int page) {
        List<MusicEntity> musicEntityList = musicRepository.findAllByOrderByHitsDesc(PageRequest.of(page, 10));
        List<GetMusicRankDto> getMusicRankDtoList = musicEntityList.stream()
                .map(musicEntity -> {
                    GetMusicRankDto getMusicRankDto = new GetMusicRankDto();
                    getMusicRankDto.setMusicId(musicEntity.getMusicId());
                    getMusicRankDto.setImage(Base64.getEncoder().encodeToString(musicEntity.getAlbumInfoEntity().getAlbumImage()));
                    getMusicRankDto.setMusicTitle(musicEntity.getMusicTitle());
                    getMusicRankDto.setSinger(musicEntity.getAlbumInfoEntity().getAlbumSinger());
                    getMusicRankDto.setAlbumId(musicEntity.getAlbumInfoEntity().getAlbumInfoId());
                    return  getMusicRankDto;
                })
                .collect(Collectors.toList());
        return getMusicRankDtoList;
    }
}
