import React from 'react';
import styled from 'styled-components';
import HerbListItem from '../pedia/HerbListItem'; // 여기에 HerbListItem 컴포넌트 경로를 정확히 적어주세요.

// List 컨테이너 스타일링
const ListContainer = styled.div`
  display: flex;
  flex-wrap: wrap;
  justify-content: flex-start; // 아이템들을 왼쪽 정렬로 시작하게 설정
  gap: 2px; // 아이템 간 간격 설정
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

