package com.example.yourstar.service.impl;

import com.example.yourstar.data.dto.music.*;
import com.example.yourstar.data.entity.AlbumEntity;
import com.example.yourstar.data.entity.AlbumInfoEntity;
import com.example.yourstar.data.entity.MusicEntity;
import com.example.yourstar.data.repository.AlbumInfoRepository;
import com.example.yourstar.data.repository.AlbumRepository;
import com.example.yourstar.data.repository.MusicRepository;
import com.example.yourstar.service.MusicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MusicServiceImpl implements MusicService {
    private AlbumInfoRepository albumInfoRepository;
    private MusicRepository musicRepository;
    private AlbumRepository albumRepository;

    @Autowired
    public  MusicServiceImpl(AlbumInfoRepository albumInfoRepository,MusicRepository musicRepository,AlbumRepository albumRepository){
        this.albumInfoRepository = albumInfoRepository;
        this.musicRepository = musicRepository;
        this.albumRepository = albumRepository;
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
    public String saveMusic(SaveMusicDto saveMusicDto) {
        try {
            MusicEntity musicEntity = new MusicEntity();
            AlbumEntity albumEntity = new AlbumEntity();

            musicEntity.setMusicTitle(saveMusicDto.getMusicTitle());
            musicEntity.setLyrics(saveMusicDto.getLyrics());
            musicEntity.setSoundSource(saveMusicDto.getSoundSourceByte());

            albumEntity.setMusicEntity(musicEntity);
            albumEntity.setAlbumInfoEntity(albumInfoRepository.getById(saveMusicDto.getAlbumInfoId()));
            albumEntity.setMusicIndex(saveMusicDto.getMusicIndex());

            musicRepository.save(musicEntity);
            albumRepository.save(albumEntity);
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
        log.info("1111");
        AlbumInfoEntity albumInfoEntity = albumInfoRepository.getById(albumId);
        GetAlbumDto getAlbumDto = new GetAlbumDto();
        getAlbumDto.setAlbumTitle(albumInfoEntity.getAlbumTitle());
        getAlbumDto.setSinger(albumInfoEntity.getAlbumSinger());
        getAlbumDto.setAlbumImage(Base64.getEncoder().encodeToString(albumInfoEntity.getAlbumImage()));
        getAlbumDto.setAlbumReleaseDate(albumInfoEntity.getAlbumReleaseDate());
        log.info("2222");
        List<AlbumEntity> albumEntityList = albumRepository.findByAlbumInfoEntity(albumInfoEntity);
        log.info("3333");
        List<MusicIdTitleDto> musicIdTitleDtoList = albumEntityList.stream()
                .map(albumEntity -> {
                    MusicIdTitleDto musicIdTitleDto = new MusicIdTitleDto();
                    musicIdTitleDto.setMusicId(albumEntity.getMusicEntity().getMusicId());
                    musicIdTitleDto.setMusicTitle(albumEntity.getMusicEntity().getMusicTitle());
                    return musicIdTitleDto;
                })
                .collect(Collectors.toList());
        log.info("4444");
        getAlbumDto.setMusicInfo(musicIdTitleDtoList);
        log.info("5555");
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

        AlbumInfoEntity albumInfoEntity = albumRepository.findDistinctAlbumInfoEntityByMusicEntity(musicEntity);
        getMusicDto.setSinger(albumInfoEntity.getAlbumSinger());
        getMusicDto.setMusicImage(Base64.getEncoder().encodeToString(albumInfoEntity.getAlbumImage()));

        return getMusicDto;
    }
}
