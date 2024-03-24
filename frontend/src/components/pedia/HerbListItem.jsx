import React from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import { PiStarBold, PiStarFill } from "react-icons/pi";

const Container = styled.div`
  display: flex; // Container를 flex로 설정하여 자식 컴포넌트를 세로로 쌓습니다.
  flex-direction: column; // 자식 컴포넌트를 세로 방향으로 정렬합니다.
  align-items: center; // 가운데 정렬합니다.
  cursor: pointer;
  margin: 10px;
`;

// 배경색이 적용되지 않은 ItemName 스타일을 정의합니다.
const ItemName = styled.div`
  margin-top: 8px; // 아이콘과의 간격 조정
  font-size: 16px; // 폰트 크기 조정
  color: #4A4A4A;
  text-align: center; // 텍스트를 중앙 정렬합니다.
  max-width: 65px; // ItemContainer와 동일한 최대 너비를 설정합니다.
  word-wrap: break-word; // 단어가 너무 길면 줄바꿈합니다.
`;

// ItemContainer에 배경색이 적용됩니다.
const ItemContainer = styled.div`
  display: flex;
  position: relative; // ItemBookMark를 올바르게 위치시키기 위해 relative로 설정
  align-items: center;
  justify-content: center;
  padding: 5px;
  background-color: #F8FFEC;
  width: 68px;
  height: 68px;
`;

const ItemBookMark = styled.div`
  position: absolute; // 부모인 ItemContainer 대비 절대 위치
  top: 1px; // 상단 가장자리에서 살짝 떨어지도록 조정
  right: 1px; // 우측 가장자리에서 살짝 떨어지도록 조정
  font-size: 17px; // 별표 크기를 조정
`;

const ItemIcon = styled.img`
  width: 55px;
`;

const HerbListItem = ({ herb }) => {
  const navigate = useNavigate();

  const handleClick = () => {
    navigate(`/detail/1`);
  };

  return (
    <Container onClick={handleClick}>
      <ItemContainer>
        <ItemIcon src={herb.acquireCheck > 0 ? "public/pediaimg/plant_acquired.svg" : "public/pediaimg/plant_isNotAcquired.svg"} />
        {herb.bookmark > 0 ? (
          <ItemBookMark>
            <PiStarFill style={{ color: '#F49349' }} />
          </ItemBookMark>
        ) : <ItemBookMark><PiStarBold style={{ color: 'F49349' }} /></ItemBookMark>}
      </ItemContainer>
      <ItemName>{herb.herbName}</ItemName>
    </Container>
  );
};

export default HerbListItem;