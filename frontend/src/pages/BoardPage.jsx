import React, { useState } from "react";
import BoardFilter from "../components/board/BoardFilter";
import BoardList from "../components/board/BoardList";
import styled from "styled-components";

const BoardPageContainer = styled.div`
  // 필요하다면 스타일을 추가하세요
`;

const BoardPage = () => {
  // 선택된 필터 옵션을 관리하는 state입니다.
  const [filterOption, setFilterOption] = useState('전체 게시글');

  return (
    <BoardPageContainer>
      <BoardFilter filterOption={filterOption} setFilterOption={setFilterOption} />
      <BoardList filterOption={filterOption} />
    </BoardPageContainer>
  );
}

export default BoardPage;