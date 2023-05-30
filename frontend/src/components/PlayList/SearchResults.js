import React from 'react';
import { useLocation } from 'react-router-dom';

const SearchResults = () => {
    const location = useLocation();
    const queryParams = new URLSearchParams(location.search);
    const searchTerm = queryParams.get('q');

    // 여기에 검색내용을 디비에 넣어서 해당 단어에 매칭되는 노래를 보여준다
    
    return (
        <div>
            <br/>
            <h1>'{searchTerm}' 검색 결과</h1>
        </div>
    );
};

export default SearchResults;
