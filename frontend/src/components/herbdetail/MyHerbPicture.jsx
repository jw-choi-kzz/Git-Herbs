import React from 'react';
import styled from 'styled-components';
import Card from '@mui/joy/Card';
import CardCover from '@mui/joy/CardCover';
import Typography from '@mui/joy/Typography';
import IconButton from '@mui/joy/IconButton';
import CreateNewFolder from '@mui/icons-material/CreateNewFolder';

// 데이터 배열 예시
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
`;

const MyHerbPicture = ({ myHerb }) => {
  const { herbId } = useParams(); // URL로부터 herbId를 가져온다
  const formatDate = (dateString) => {
    const options = { year: 'numeric', month: '2-digit', day: '2-digit' };
    return new Date(dateString).toLocaleDateString('ko-KR', options);
  };
  // 이미지 경로를 매핑하는 함수
  const getImageUrl = (imgId) => {
    return `/herbs/002_plant_userpic${imgId}.png`;
  };

  // 현재 페이지의 herbId와 myHerbId가 일치하는지 확인
  if (myHerb.myHerbId.toString() !== herbId) {
      return null; // 일치하지 않으면 아무것도 렌더링하지 않음
  }
  
  return (
    <Card variant="outlined" sx={{ maxWidth: 345 }}>
      <CardCover>
        <img 
          src={getImageUrl(myHerb.imgId)} // 경로 확인 필요
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
        Similarity: {myHerb.similarity}%
      </Typography>
      <Typography variant="body2" color="text.secondary">
        Added on: {formatDate(myHerb.createdAt)}
      </Typography>
    </Card>
  );
};

export default MyHerbPicture;
