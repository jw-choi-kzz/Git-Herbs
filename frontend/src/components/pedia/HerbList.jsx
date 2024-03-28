import React from 'react';
import styled from 'styled-components';
import HerbListItem from '../pedia/HerbListItem';

const ListContainer = styled.div`
  display: flex;
  flex-wrap: wrap;
  justify-content: center; // 아이템들을 가운데 정렬로 시작하게 설정
  gap: 2px; // 아이템 간 간격 설정
  margin: auto; // 상하좌우 마진 자동으로 설정하여 가운데 정렬
  width: fit-content; // 컨텐츠 크기에 맞게 가로 폭을 설정
  max-width: 100%; // 컨테이너의 최대 폭이 화면 폭을 넘지 않도록 설정
`;

const HerbList = ({ herbsData }) => {
  return (
    // 리스트 컨테이너 안에서 스크롤바 안보이게 설정한 것
    // <ListContainer style={{ overflowY: 'auto', height: 'calc(100vh - 200px)',  justifyContent: 'center', scrollbarWidth: 'none' }}>
    <ListContainer>
      {herbsData.map((herb) => (
        <HerbListItem key={herb.herbId} herb={herb} />
      ))}
    </ListContainer>
  );
};

export default HerbList;

