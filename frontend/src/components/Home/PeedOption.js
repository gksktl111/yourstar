import React, { useCallback } from 'react';
import './PeedOption.css';
import { useDispatch } from 'react-redux';
import { optionModalOff } from '../../store/Store';

const PeedOption = () => {
    const dispatch = useDispatch();

    const handleModalOff = useCallback(
        (e) => {
            // 클릭이벤트가 모달 내부에서 발생한 경우(e.stopPropagation() 으로 버블링 차단한 경우)
            if (e.currentTarget !== e.target) {
                return;
            }

            // 클릭이벤트가 배경에서 발생한 경우
            dispatch(optionModalOff());
        },
        [dispatch],
    );

    return (
        <div className="peed_option_background"
             onClick={handleModalOff}>
            <div className="peed_option_container">
                <div className="peed_option_row">
                    <div
                        className="peed_option_col"
                        style={{
                            color: 'red',
                            fontWeight: 'bold',
                        }}
                    >
                        신고
                    </div>
                    {/*팔로우 취소 부분은 팔로우가 되어있는 경우만 팔로우 취소 나타내기*/}
                    {/*<div*/}
                    {/*    className="peed_option_col"*/}
                    {/*    style={{*/}
                    {/*        color: 'red',*/}
                    {/*        fontWeight: 'bold',*/}
                    {/*    }}*/}
                    {/*>*/}
                    {/*    팔로우 취소*/}
                    {/*</div>*/}
                    <a className="peed_option_col"
                       href = {`http://localhost:3000/alsrb_1214`}
                        style = {{color : 'black'}}
                    >프로필로 이동</a>
                    <div className="peed_option_col">저장</div>
                    <div className="peed_option_col"
                         onClick={() => {dispatch(optionModalOff())}}>취소</div>
                </div>
            </div>
        </div>
    );
};

export default PeedOption;
