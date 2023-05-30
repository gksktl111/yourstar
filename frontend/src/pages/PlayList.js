import React, {useState} from 'react';
import './PlayList.css';
import {BsPlayFill, BsThreeDotsVertical} from "react-icons/bs";
import {AiOutlineFolderAdd} from "react-icons/ai";
import {RiPlayListAddFill} from "react-icons/ri";
import {useNavigate} from "react-router-dom";

const PlayList = () => {
    const [searchTerm, setSearchTerm] = useState('');
    const [currentIndex, setCurrentIndex] = useState(0);


    // 추천 목록
    const recoList = Array.from({length: 15}, (_, i) => ({
        title: `어떤 노래 ${i + 1}`,
        artist: `가수 ${String.fromCharCode(65 + i)}`,
        rank: i + 1,
    }));

    // 초기 차트 목록
    const songs = Array.from({length: 30}, (_, i) => ({
        title: `어떤 노래 ${i + 1}`,
        artist: `가수 ${String.fromCharCode(65 + i)}`,
        rank: i + 1,
    }));

    // 차트 불러오기 관리
    const [loadedSongs, setLoadedSongs] = useState(songs);

    // 차트 불러오기
    const loadMoreSongs = () => {
        const newSongs = Array.from({length: 70}, (_, i) => ({
            title: `어떤 노래 ${loadedSongs.length + i + 1}`,
            artist: `가수 ${String.fromCharCode(65 + (loadedSongs.length + i) % 26)}`,
            rank: loadedSongs.length + i + 1,
        }));
        setLoadedSongs([...loadedSongs, ...newSongs]);
    };

    // 차트 더보기 관리
    const [expanded, setExpanded] = useState(false);

    //  더보기 더보기 접기
    const toggleExpand = () => {
        setExpanded(!expanded);
        if (!expanded) {
            loadMoreSongs();
        } else {
            setLoadedSongs(loadedSongs.slice(0, 30));
        }
    };

    const handleSearchChange = (event) => {
        setSearchTerm(event.target.value);
    };

    // 슬라이드 3개씩 나오도록
    const visibleReco = 3;

    const goToPrevSlide = () => {
        setCurrentIndex((currentIndex - visibleReco + recoList.length) % recoList.length); // 수정된 코드
    };

    const goToNextSlide = () => {
        setCurrentIndex((currentIndex + visibleReco) % recoList.length); // 수정된 코드
    };

    // 노래 슬라이더
    const renderSongs = () => {
        return recoList.map((song, i) => (
            <div
                key={i}
                className="song-card"
                style={{
                    display: i >= currentIndex && i < currentIndex + visibleReco ? "block" : "none",
                    marginRight: i < currentIndex + visibleReco - 1 ? "20px" : "",
                }}
            >
                <div className="thumbnail">
                    <img src={`/assets/img/${song.rank}.jpg`} alt="이미지 없음"/>
                </div>
                <div className="song-details">
                    <h3 className="title">{song.title}</h3>
                    <p className="artist">{song.artist}</p>
                </div>
            </div>
        ));
    };

    // 슬라이더 페이지용 불릿
    const renderDots = () => {
        const totalPages = Math.ceil(recoList.length / visibleReco);
        return Array.from({length: totalPages}, (_, i) => (
            <span
                key={i}
                className="dot"
                style={{
                    backgroundColor: i * visibleReco === currentIndex ? "gray" : "lightgray",
                }}
                onClick={() => setCurrentIndex(i * visibleReco)}
            ></span>
        ));
    };

    // 음악 차트 헤더 렌더링
    const renderChartHeader = () => {
        return (
            <div className="chart-header">
                <input className="checkbox-header" type={"checkbox"}/>
                <span className="rank-header">순위</span>
                <span className="track-album-header">곡/앨범</span>
                <span className="artist-header">제목/아티스트</span>
                <div className="list-controls-header">
                    <span style={{marginRight : "7px"}}>듣기</span>
                    <span>목록추가</span>
                    <span>내 리스트</span>
                    <span>더보기</span>
                </div>
            </div>

        );
    };

    // 음악 차트 렌더링
    const renderMusicChart = () => {
        return loadedSongs.map((song, i) => (
            <div key={i} className="chart-item">
                <input type={"checkbox"}/>
                <span className="rank">{song.rank}</span>
                <div className="cover_wrapper">
                    <img src={`/assets/img/${song.rank}.jpg`} alt={song.title + " cover"} className="cover_image"/>
                </div>
                <div className="rank_title_artist_wrapper">
                        <span className="title-artist">
                            <span className="chart-title">{song.title}</span>
                        </span>
                    <span className="chart-artist">{song.artist}</span>
                </div>
                <div className="list-controls-icons">
                    <span className="icons-right">
                        <BsPlayFill/>
                    </span>

                    <span className="icons-right">
                        <RiPlayListAddFill style={{marginRight : "10px"}}/>
                    </span>

                    <span className="icons-right">
                        <AiOutlineFolderAdd/>
                    </span>

                    <span className="icons-right">
                        <BsThreeDotsVertical/>
                    </span>
                </div>
            </div>
        ));
    };

    const navigate = useNavigate(); // 수정된 line

    const handleSubmit = (event) => {
        event.preventDefault();
        if (searchTerm.trim()) {
            navigate(`/search?q=${searchTerm.trim()}`);
            setSearchTerm("");
        }
    };

    return (
        <div className="playlist">
            <form onSubmit={handleSubmit} className="search-bar">
                <input
                    type="text"
                    placeholder="검색어를 입력하세요"
                    value={searchTerm}
                    onChange={handleSearchChange}
                />
            </form>
            <div className="playlist-title">오늘의 추천 리스트</div>
            <div className="song-list">
                <button
                    className={`arrow-btn left-arrow ${
                        currentIndex === 0 ? "hidden" : ""
                    }`}
                    onClick={currentIndex !== 0 ? goToPrevSlide : undefined} // 이전 버튼 비활성화에 대한 변경
                >
                    {"<"}
                </button>

                <div className="slider">{renderSongs()}</div>

                <button
                    className={`arrow-btn right-arrow ${
                        currentIndex + visibleReco >= recoList.length ? "hidden" : "" // 수정된 코드
                    }`}
                    onClick={
                        currentIndex + visibleReco < recoList.length ? goToNextSlide : undefined // 다음 버튼 비활성화에 대한 변경 및 수정된 코드
                    }
                >
                    {">"}
                </button>
            </div>
            <div className="slider-dots">{renderDots()}</div>

            {/* 수정된 부분 : 음악 차트 추가 */}
            <div className="music-chart">
                <h2>오늘 TOP 100</h2>
                <div style={{marginBottom: "300px"}}>
                    {renderChartHeader()}
                    <div className="chart-container">{renderMusicChart()}</div>
                    <button className="load-more-btn" onClick={toggleExpand}>
                        {expanded ? "더보기 접기" : "더보기"}
                    </button>
                </div>
            </div>

        </div>
    );
};

export default PlayList;
