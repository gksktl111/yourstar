import React, {useEffect, useState} from 'react';
import './NewPeed.css';
import {useDispatch} from 'react-redux';
import axios from 'axios';
import {newPeedModalOff} from '../../store/Store';
import {IoClose} from 'react-icons/io5';

const NewPeed = () => {
    const dispatch = useDispatch();
    const [file, setFile] = useState(null);
    const [fileType, setFileType] = useState('');
    const [description, setDescription] = useState('');

    const [fileUrl, setFileUrl] = useState("");

    // 한국 시간 가져오기
    const getKST = () => {
        const offset = 9 * 60; // 한국 표준시는 UTC+9 이므로, offset은 9시간(분 단위)입니다.
        const now = new Date(); // 현재 시간을 가져옵니다.
        const utc = now.getTime() + now.getTimezoneOffset() * 60 * 1000; // UTC 시간을 구합니다.
        return new Date(utc + offset * 60 * 1000); // UTC 시간에 offset을 더하여 KST를 구합니다.
    }

    const kst = getKST();

    useEffect(() => {
        // 파일 변경 시에만 URL이 변함
        if (file) {
            setFileUrl(URL.createObjectURL(file));
        } else {
            setFileUrl("");
        }
    }, [file]);


    const handleFileChange = (e) => {
        if (e.target.files[0]) {
            setFile(e.target.files[0]);
            setFileType(e.target.files[0].type);
        }
    };

    const handleSubmit = async () => {
        const formData = new FormData();
        formData.append('userId', localStorage.getItem("token"));
        formData.append('contents', description);
        formData.append('meta', file);
        formData.append('postTime', kst);

        // console.log(localStorage.getItem("token"));
        // console.log(file);
        // console.log(description)
        // console.log('Current KST:', kst);

        try {
            await axios.post('/post/writePost', formData);
            alert('성곡적으로 게시물을 작성했습니다!')
            dispatch(newPeedModalOff());
        } catch (error) {
            alert('게시물 작성에 실패했습니다')
            console.error(error);
        }
    };

    return (
        <div className="modal_background">
            <div className="new-peed-modal-close" onClick={() => dispatch(newPeedModalOff())}>
                <IoClose/>
            </div>
            <div className="new-peed-modal">
                <div className='new-peed-header'>
                    <h2>새 게시물 작성</h2>
                </div>
                <div className="new-peed-modal-left">
                    {file && fileType && fileType.startsWith("image/") && (
                        <img
                            src={fileUrl}
                            alt="Preview"
                            className="new-peed-file-preview"
                        />
                    )}
                    {file && fileType && fileType.startsWith("video/") && (
                        <>
                            <video
                                src={fileUrl}
                                alt="Preview"
                                className="new-peed-file-preview"
                                controls
                            />
                            <div className="new-peed-file-name">
                                파일이름 : {file.name}
                            </div>
                        </>
                    )}
                    {file && fileType && fileType.startsWith("audio/") && (
                        <>
                            <audio
                                src={fileUrl}
                                className="new-peed-file-preview"
                                controls
                            />
                            <div className="new-peed-file-name">
                                파일이름 : {file.name}
                            </div>
                        </>
                    )}


                </div>
                <div className="new-peed-modal-right">
                    <textarea
                        className="new-peed-description"
                        onChange={(e) => setDescription(e.target.value)}
                        value={description}
                        placeholder="피드에 대한 설명을 작성해주세요."
                    />
                    <button onClick={handleSubmit} className="new-peed-submit-button">
                        게시
                    </button>
                    <label htmlFor="new-peed-file-input" className="new-peed-file-label">
                        파일 선택
                    </label>
                    <input
                        id="new-peed-file-input"
                        type="file"
                        accept="image/*, video/*, audio/*"
                        onChange={handleFileChange}
                        className="new-peed-file-input"
                    />
                </div>
            </div>
        </div>
    );
};

export default NewPeed;
