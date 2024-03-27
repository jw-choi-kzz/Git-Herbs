import React from 'react';
import styled from 'styled-components';
import Card from '@mui/joy/Card';
import CardCover from '@mui/joy/CardCover';
import Typography from '@mui/joy/Typography';
import IconButton from '@mui/joy/IconButton';
import CreateNewFolder from '@mui/icons-material/CreateNewFolder';

const CardContainer = styled.div`
  border: 1px solid #ddd;
  border-radius: 8px;
  overflow: hidden;
  margin-bottom: 16px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
`;

const HerbImage = styled.img`
  width: 100%;
  display: block;
`;

const HerbDetails = styled.div`
  padding: 8px;
  background: #f4f4f4;
`;

const Similarity = styled.div`
  font-size: 14px;
  color: #666;
`;

const DateStamp = styled.div`
  font-size: 12px;
  color: #999;
`

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
    return `.../herbs/002_plant_userpic${imgId}.png`;
  };

  if (!herbId) {
    return <Typography>No Herb ID provided.</Typography>;
  }

  //const parsedHerbId = parseInt(herbId, 10);

    // fetchHerbDetail(herbId);
  console.log("herbData:", herbData); // 로깅: 데이터 확인
  console.log("herbId:", herbId); // 로깅: herbId 확인


    // herbId에 해당하는 데이터만 필터링
  const filteredHerbPictures = herbData.filter(herbData => herbData.myHerbId === parseInt(herbId, 10));


    console.log("filteredHerbPictures:", filteredHerbPictures); // 로깅: 필터링된 결과 확인

    if (!filteredHerbPictures.length) {
      return <Typography>No matching herb picture found.</Typography>;
    }  
  
  return filteredHerbPictures.map((herbData, index) => (
    <CardContainer key={index} variant="outlined" sx={{ maxWidth: 345 }}>
      <CardCover>
        <HerbImage 
          src={getImageUrl(herbData.imgId)} // 경로 확인 필요
          alt="Herb"
          style={{ width: '100%', height: 'auto' }}
        />
        <IconButton
          sx={{ position: 'absolute', top: 8, right: 8 }}
          aria-label="add to favorites"
        >
          <CreateNewFolder />
        </IconButton>
      </CardCover>
      <Typography gutterBottom variant="h5" component="div">
        Similarity: {herbData.similarity}%
      </Typography>
      <Typography variant="body2" color="text.secondary">
        Added on: {formatDate(herbData.createdAt)}
      </Typography>
    </CardContainer>
  ));
};

export default MyHerbPicture;
