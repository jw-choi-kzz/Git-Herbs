import React, { useEffect, useState } from "react";
import BoardListItem from "./BoardListItem";
import boardService from "../../apis/board";
import { configService } from "../../apis/config";
import styled from "styled-components";

const StyledDiv = styled.div`
  margin-top: 20px;
  margin-bottom: 10px;
`;

const BoardList = ({ filterOption }) => {

  const [boardDatas, setBoardDatas] = useState([]);

  useEffect(() => {
    fetchData();
  }, [filterOption]);

  const fetchData = async () => {
    try {
      let response = [];
      const loginconfig = await configService.loginConfig();
      
      // filterOption에 따라 적절한 함수 호출
      if (filterOption === '내가 쓴 글 모아보기') {
        response = await boardService.getMyBoard(loginconfig);
      } else if (filterOption === '좋아요한 글 모아보기') {
        response = await boardService.getFavoriteBoard(loginconfig);
      } else {
        response = await boardService.getBoard(loginconfig);
      }
      
      setBoardDatas(response);
      console.log(response);
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <StyledDiv>
      {boardDatas.map((data, index) => (
        <BoardListItem key={index} data={data} />
      ))}
    </StyledDiv>
  );
};

export default BoardList;
