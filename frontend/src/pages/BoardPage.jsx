import React, { useState } from "react";
import BoardFilter from "../components/board/BoardFilter";
import BoardList from "../components/board/BoardList";
import LoginModal from "../components/LoginModal";
import useLoginStore from "../store/useLoginStore";
import styled from "styled-components";

const BoardPageContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 10px;
  position: relative;
`;

const Title = styled.h1`
  width: 100%;
  font-size: 30px;
  text-align: left;
  // color: #4a4a4a;
  margin-top: 10px;
  margin-left: 10px;
  margin-bottom: 10px;
  margin-right: 10px;
`;
const FilterWrapper = styled.div`
  position: absolute;
  top: 27px; // 상단 여백 설정
  right: 20px; // 우측 여백 설정
  z-index: 10px; // 너비 설정
`;

const BoardPage = () => {
  const [filterOption, setFilterOption] = useState("전체 게시글");
  const [isLoginModalOpen, setIsLoginModalOpen] = useState(false);
  const { accessToken } = useLoginStore(); // Access the login state

  const handleFilterChange = (newValue) => {
    if ((newValue === '내가 쓴 글 모아보기' || newValue === '좋아요한 글 모아보기') && !accessToken) {
      setIsLoginModalOpen(true); // Open login modal if not logged in
    } else {
      setFilterOption(newValue);
    }
  };

  return (
    <BoardPageContainer>
      <Title>심봤다 게시판</Title>
      <FilterWrapper>
      <BoardFilter
        filterOption={filterOption}
        setFilterOption={handleFilterChange}
      />
      </FilterWrapper>
      <BoardList filterOption={filterOption} />
      {isLoginModalOpen && (
        <LoginModal 
          isOpen={isLoginModalOpen} 
          onClose={() => setIsLoginModalOpen(false)} 
        />
      )}
    </BoardPageContainer>
  );
};

export default BoardPage;
