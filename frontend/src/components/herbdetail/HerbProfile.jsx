import React from 'react';
import styled from 'styled-components';
import { PiStarFill, PiStarBold } from "react-icons/pi";

const Container = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin: 16px;
  /* 여기에 필요한 추가 스타일링을 적용하세요 */
`;

const HerbName = styled.h1`
  font-size: 24px;
  /* 여기에 필요한 추가 스타일링을 적용하세요 */
`;

const HerbScientificName = styled.h2`
  font-size: 16px;
  color: #757575;
  /* 여기에 필요한 추가 스타일링을 적용하세요 */
`;

// 북마크 상태에 따라 색상을 동적으로 적용할 수 있는 스타일 컴포넌트
const BookmarkIcon = styled.div`
  color: #F49349; /* 북마크 색상 */
  /* 여기에 필요한 추가 스타일링을 적용하세요 */
`;

const HerbProfile = ({ data }) => {
  // 이 부분에서 bookmark 값이 있는지 확인하고, 없다면 임의의 값으로 설정합니다.
  // 추후에 실제 bookmark 데이터가 추가되면 해당 데이터를 사용하시면 됩니다.
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
