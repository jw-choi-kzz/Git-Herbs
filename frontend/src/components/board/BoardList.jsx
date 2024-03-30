import React, { useEffect, useState } from "react";
import BoardListItem from "./BoardListItem";
import boardService from "../../apis/board";
import { configService } from "../../apis/config";

const BoardList = ({ filterOption }) => {
  // 게시물 데이터를 state로 관리합니다.

  const [boardDatas, setBoardDatas] = useState(null);

  useEffect(() => {
    getboard();
  }, []);

  const getboard =  async () =>{
    boardService.getBoard(loginconfig)
    .then(response =>{
      setBoardDatas(response);
    })
    .catch(error =>{
      console.log(error);
    })
  }

  const loginconfig = configService.loginConfig();


  const [boardData, setBoardData] = useState([
    // createdAt 추가함, 게시물 생성될 때의 시간 의미(날짜 표시를 위해서 추가함, 정렬은 boardId로 됨)
    {
      "boardId": "1.",
      "userId": "2.",
      "userImgUrl": "/profileimg/부아아앙.jpg",
      "userNickname": "부아아앙",
      "imgUrl": "/herbs/001_00000106_flower.jpg",
      "likeCnt": "12",
      "likeCheck": true,
      "herbName": "칡",
      "similar": 50,
      "herbId": 2,
      "createdAt": "2024-03-08T17:01:30.007084"
    },
    {
      "boardId": "2.",
      "userId": "1.",
      "userNickname": "최고봉심마니",
      "userImgUrl": "/profileimg/최고봉심마니.png",
      "imgUrl": "/herbs/002_plant_userpic1.png",
      "likeCnt": "568",
      "likeCheck": true,
      "herbName": "당귀",
      "similar": 90,
      "herbId": 1,
      "createdAt": "2024-03-13T09:00:00"
    },
    {
      "boardId": "3.",
      "userId": "1.",
      "userImgUrl": "/profileimg/최고봉심마니.png",
      "userNickname": "최고봉심마니",
      "imgUrl": "/herbs/002_plant_userpic2.png",
      "likeCnt": "325",
      "likeCheck": false,
      "herbName": "당귀",
      "similar": 50,
      "herbId": 1,
      "createdAt": "2024-03-18T16:31:06.004408"
    },
  ]);

  const getFilteredData = () => {
    let filteredData = [...boardData]; // 새 배열을 생성하여 원본 데이터를 유지
    
    // 필터링 조건을 적용합니다.
    if (filterOption === '내가 쓴 글 모아보기') {
      // userId 일치 여부 확인하는 코드는 주석 처리
      // 추후에 로그인 로직에 따라 '1.'을 현재 로그인한 사용자의 ID로 대체
      filteredData = filteredData.filter(board => /* board.userId === loggedInUserId && */ board.userId === '1.');
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