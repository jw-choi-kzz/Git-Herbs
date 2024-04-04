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
    <ListContainer>
      {herbsData.map((herb) => (
        <HerbListItem key={herb.herbId} herb={herb} />
      ))}
    </ListContainer>
  );
};

export default HerbList;
