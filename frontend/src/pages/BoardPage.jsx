import React, { useState } from "react";
import BoardFilter from "../components/board/BoardFilter";
import BoardList from "../components/board/BoardList";
import styled from "styled-components";

const BoardPageContainer = styled.div``;

const BoardPage = () => {
  const [filterOption, setFilterOption] = useState("전체 게시글");

  return (
    <BoardPageContainer>
      <BoardFilter
        filterOption={filterOption}
        setFilterOption={setFilterOption}
      />
      <BoardList filterOption={filterOption} />
    </BoardPageContainer>
  );
};

export default BoardPage;
