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
        formData.append('file', file);
        formData.append('description', description);

        console.log(file);
        console.log(description)

        try {
            await axios.post('/api/peeds', formData);
            dispatch(newPeedModalOff());
        } catch (error) {
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
