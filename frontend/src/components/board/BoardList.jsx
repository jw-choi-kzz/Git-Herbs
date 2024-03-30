import React, { useEffect, useState } from "react";
import BoardListItem from "./BoardListItem";
import boardService from "../../apis/board";
import { configService } from "../../apis/config";

const BoardList = ({ filterOption }) => {

  const [boardDatas, setBoardDatas] = useState([]);

  useEffect(() => {
    getBoard();
  }, []);

  const getBoard = async () => {
    try {
      const loginconfig = await configService.loginConfig();
      const response = await boardService.getBoard(loginconfig);
      setBoardDatas(response);
      console.log(response);
    } catch (error) {
      console.log(error);
    }
  };


  const getFilteredData = () => {
    let filteredData = [...boardDatas]; // 새 배열을 생성하여 원본 데이터를 유지
    
    // 필터링 조건을 적용합니다.
    if (filterOption === '내가 쓴 글 모아보기') {
      // userId 일치 여부 확인하는 코드는 주석 처리
      // 추후에 로그인 로직에 따라 '1.'을 현재 로그인한 사용자의 ID로 대체
      filteredData = filteredData.filter(board => /* board.userId === loggedInUserId && */ board.userId === '1');
    } else if (filterOption === '좋아요한 글 모아보기') {
      filteredData = filteredData.filter(board => board.likeCheck === true);
    }
    // boardId를 기준으로 내림차순 정렬합니다.
    filteredData.sort((a, b) => parseFloat(b.boardId) - parseFloat(a.boardId));
    return filteredData;
  };

  const filteredBoardData = getFilteredData();

  return (
    <div>
      {filteredBoardData.map((data, index) => (
        <BoardListItem key={index} data={data} />
      ))}
    </div>
  );
};

export default BoardList;
