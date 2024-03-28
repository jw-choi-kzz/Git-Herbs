import React, { useState } from 'react';
import styled from 'styled-components';
import Typography from '@mui/joy/Typography';
import Button from '@mui/joy/Button';
import { FaCirclePlus } from "react-icons/fa6";
import MySnackbar from '../MySnackbar';

const CardContainer = styled.div`
  border-radius: 12px;
  max-width: 300px;
  margin: 0 auto; // 수평 중앙 정렬
  overflow: hidden;
  margin-bottom: 16px;
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
`;

const HerbImage = styled.img`
  width: 100%;
  border-radius: 8px;
  object-fit: cover; // 이미지 비율을 유지하며 카드에 맞게 조정
`;

const HerbDetails = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 2px 5px;
  background: #f4f4f4;
`;

const StyledSimilarity = styled(Typography)`
&& {
  font-size: 12px;
  color: #999;
  font-weight: normal;
  white-space: nowrap; // 글자가 줄바꿈 되지 않도록 설정
  //overflow: hidden;
  text-overflow: ellipsis;
}
`;

const StyledDateStamp = styled(Typography)`
&& {
  font-size: 20px;
  color: #666;
  font-weight: normal;
  flex-grow: 1; // 왼쪽에 여백을 없애고 여유 공간을 모두 차지하도록 조정
}
`;

const StyledFaCirclePlus = styled(FaCirclePlus)`
  color: #407700;
  font-size: 40px;
  margin-right: 4px;
`;

// // `IconButton`에 직접 스타일을 적용하기 위해 `styled` 사용
// const StyledIconButton = styled(IconButton)`
//   position: absolute;
//   top: 8px;
//   right: 8px;
// `;

const MyHerbPicture = ({ herbId }) => {
  // 로그인 체크 로직 (가정)
  // const isLoggedIn = checkLogin();

  const herbData = [
    {
      "myHerbId": 1,
      "imgId": "2",
      "similarity": 56.37,
      "createdAt": "2024-03-18T17:01:30.007084"
    },
    {
      "myHerbId": 8,
      "imgId": "1",
      "similarity": 80.2,
      "createdAt": "2024-03-18T16:31:06.004408"
    },
    {
      "myHerbId": 1,
      "imgId": "1",
      "similarity": 80.12,
      "createdAt": "2024-03-13T09:00:00"
    }
  ];

  const formatDate = (dateString) => {
    const date = new Date(dateString);
    const year = date.getFullYear().toString().slice(-2); // 연도의 마지막 두 자리
    const month = date.getMonth() + 1; // getMonth()는 0부터 시작하므로 1을 더함
    const day = date.getDate();

    const formattedMonth = month < 10 ? `0${month}` : month;
    const formattedDay = day < 10 ? `0${day}` : day;

    return `${year}.${formattedMonth}.${formattedDay}`;
  };

  const getImageUrl = (imgId) => {
    return `/herbs/002_plant_userpic${imgId}.png`;
  };

  const [snackbarOpen, setSnackbarOpen] = useState(false);
  const [snackbarStep, setSnackbarStep] = useState(1);

  const handlePlusClick = () => {
    setSnackbarStep(1);
    setSnackbarOpen(true);
  };

  const handleCloseSnackbar = () => {
    setSnackbarOpen(false);
  };

  const handleRegisterClick = () => {
    setSnackbarStep(2);
  };

  const snackbarContent = snackbarStep === 1 ? (
    <>
      <Typography>'심봤다 게시판'에 사진을 등록할까요?</Typography>
      <Button onClick={handleCloseSnackbar}>취소</Button>
      <Button onClick={handleRegisterClick}>등록하기</Button>
    </>
  ) : (
    <>
      <Typography>'심봤다 게시판'에 사진 등록이 완료되었습니다!</Typography>
      <Button onClick={handleCloseSnackbar}>확인하기</Button>
    </>
  );

  if (!herbId) {
    return <Typography>No Herb ID provided.</Typography>;
  }

  // fetchHerbDetail(herbId);

  // herbId에 해당하는 데이터만 필터링
  const filteredHerbPictures = herbData.filter(herbData => herbData.myHerbId === parseInt(herbId, 10));
  if (!filteredHerbPictures.length) {
    return <Typography>No matching herb picture found.</Typography>;
  }

  return (
    <>
      {herbData.filter(data => data.myHerbId === parseInt(herbId, 10)).map((herbData, index) => (
        <CardContainer key={index}>
          <HerbImage src={getImageUrl(herbData.imgId)} alt="Herb" />
          <HerbDetails>
            <StyledDateStamp>{formatDate(herbData.createdAt)}</StyledDateStamp>
            <StyledSimilarity>유사도: {herbData.similarity}%</StyledSimilarity>
            <StyledFaCirclePlus onClick={handlePlusClick} />
          </HerbDetails>
        </CardContainer>
      ))}

      <MySnackbar open={snackbarOpen} onClose={handleCloseSnackbar}>
        {snackbarContent}
      </MySnackbar>
    </>
  );
};

export default MyHerbPicture;