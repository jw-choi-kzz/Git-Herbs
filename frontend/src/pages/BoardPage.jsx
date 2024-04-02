import React, { useState } from "react";
import BoardFilter from "../components/board/BoardFilter";
import BoardList from "../components/board/BoardList";
import LoginModal from "../components/LoginModal";
import useLoginStore from "../store/useLoginStore";
import styled from "styled-components";

const BoardPageContainer = styled.div``;
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
      <BoardFilter
        filterOption={filterOption}
        setFilterOption={handleFilterChange}
      />
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
