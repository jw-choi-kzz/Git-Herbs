import React from 'react';
import styled from 'styled-components';
import { PiStarFill, PiStarBold } from "react-icons/pi";
import { herbsService } from '../../apis/herbs';
import { configService } from '../../apis/config';


const Container = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin: 12px;
`;

const HerbName = styled.h1`
  font-size: 30px;
  margin-bottom: 0px;
`;

const HerbScientificName = styled.h2`
  font-size: 15px;
  font-weight: normal;
  color: #757575;
`;

// 북마크 상태에 따라 색상을 동적으로 적용할 수 있는 스타일 컴포넌트
const BookmarkIcon = styled.div`
  color: #F49349;
  font-size: 30px;
`;

const HerbProfile = ({ data }) => {
  const isBookmarked = data.herbBookmark > 0;

  
  return (
    <Container>
      <div>
        <HerbName>{data.herbName}</HerbName>
        <HerbScientificName>{data.herbScientificName}</HerbScientificName>
      </div>
      <BookmarkIcon>
        {isBookmarked ? <PiStarFill /> : <PiStarBold />}
      </BookmarkIcon>
    </Container>
  );
};

export default HerbProfile;
