import React from "react";
import styled from "styled-components";
import HerbListItem from "../pedia/HerbListItem";

const ListContainer = styled.div`
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  gap: 2px;
  margin: auto;
  width: fit-content;
  max-width: 100%;
`;

const HerbList = ({ herbsData }) => {
  return (
    // 리스트 컨테이너 안에서 스크롤바 안보이게 설정한 것
    <ListContainer>
      {herbsData.map((herb) => (
        <HerbListItem key={herb.herbId} herb={herb} />
      ))}
    </ListContainer>
  );
};

export default HerbList;
