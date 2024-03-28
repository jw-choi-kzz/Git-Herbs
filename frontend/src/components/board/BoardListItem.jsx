import React from 'react';
import styled from 'styled-components';
import Typography from '@mui/joy/Typography';
import Button from '@mui/joy/Button';
import { AiOutlineHeart, AiFillHeart } from "react-icons/ai"; // 좋아요 아이콘을 위한 React Icons import

const CardContainer = styled.div`
  border-radius: 12px;
  max-width: 300px;
  margin: 0 auto;
  overflow: hidden;
  margin-bottom: 16px;
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
`;

const HerbImage = styled.img`
  width: 100%;
  height: auto;
  max-height: 300px;
  object-fit: cover;
`;

const UserSection = styled.div`
  display: flex;
  align-items: center;
  padding: 8px;
  background: #f4f4f4;
`;

const UserInfo = styled.div`
  margin-left: 8px;
`;

const UserAvatar = styled.img`
  width: 40px;
  height: 40px;
  border-radius: 50%;
`;

const LikeCounter = styled.div`
  display: flex;
  align-items: center;
  margin-left: auto;
`;

const StyledHeartIcon = styled(AiFillHeart)`
  color: red;
  margin-right: 4px;
`;

const StyledEmptyHeartIcon = styled(AiOutlineHeart)`
  color: #407700;
  margin-right: 4px;
`;

const BoardListItem = ({ data }) => {
  const {
    imgUrl,
    likeCnt,
    herbName,
    userNickname,
    userImgUrl,
    createdAt,
    likeCheck
  } = data;

  // 사용자가 좋아요를 눌렀는지 여부에 따라 아이콘을 조정합니다.
  const HeartIcon = likeCheck ? StyledHeartIcon : StyledEmptyHeartIcon;

  const formatDate = (dateString) => {
    const date = new Date(dateString);
    const year = date.getFullYear().toString().slice(-2); // 연도의 마지막 두 자리
    const month = (date.getMonth() + 1).toString().padStart(2, '0'); // getMonth()는 0부터 시작하므로 1을 더하고, 2자리 형태로
    const day = date.getDate().toString().padStart(2, '0'); // 2자리 형태로

    // const formattedMonth = month < 10 ? `0${month}` : month;
    // const formattedDay = day < 10 ? `0${day}` : day;

    return `${year}.${month}.${day}`;
  };

  return (
    <CardContainer>
      <HerbImage src={imgUrl} alt={herbName} />
      <UserSection>
        <UserAvatar src={userImgUrl} alt={userNickname} />
        <UserInfo>
          <Typography>{userNickname}</Typography>
          <Typography>{formatDate(createdAt)}</Typography>
        </UserInfo>
        <LikeCounter>
          <HeartIcon />
          <Typography>{likeCnt}</Typography>
        </LikeCounter>
      </UserSection>
    </CardContainer>
  );
};

export default BoardListItem;