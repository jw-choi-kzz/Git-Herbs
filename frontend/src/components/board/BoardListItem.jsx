import React, { useEffect, useState } from "react";
import styled from "styled-components";
import Typography from "@mui/joy/Typography";
import Button from "@mui/joy/Button";
import { AiOutlineHeart, AiFillHeart } from "react-icons/ai"; // 좋아요 아이콘을 위한 React Icons import
import boardService from "../../apis/board";
import { configService } from "../../apis/config";
import LoginModal from "../LoginModal";
import useLoginStore from "../../store/useLoginStore";

const CardContainer = styled.div`
  display: flex; // 이미지와 유저 섹션을 위한 flex 컨테이너
  flex-direction: column; // 세로 방향 정렬
  justify-content: space-between; // 유저 섹션을 하단에 배치
  border-radius: 12px;
width: 80%;
  max-width: 336px;
  margin: 0 auto;
  overflow: hidden;
  margin-bottom: 25px;
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
  min-height: 300px; // 이미지와 유저 섹션을 포함한 최소 높이 설정
  
@media (max-width: 336px) {
    max-width: 100%; // 화면 폭이 500px 이하일 때는 최대 폭을 100%로 설정
  }
`;

const ImageContainer = styled.div`
  display: flex; // 이미지를 위한 flex 컨테이너
  justify-content: center; // 이미지를 중앙에 위치
  align-items: center; // 이미지를 중앙에 위치
  flex-grow: 1; // 이미지가 유연하게 늘어나도록 설정
  background: #f4f4f4; // 배경 색상 설정
`;

const HerbImage = styled.img`
  width: 100%;
  max-height: 100%;
  object-fit: contain; // 이미지가 컨테이너를 벗어나지 않고 비율 유지
`;

const UserSection = styled.div`
  display: flex;
  align-items: center;
  padding: 8px;
  background: #f4f4f4;
  border-top: 1px solid #dcdcdc; // 유저 섹션 상단에 선 추가
`;


const UserInfo = styled.div`
  margin-left: 8px;
  white-space: normal; /* 텍스트가 경계를 넘어갈 때 줄바꿈 */
  overflow-wrap: break-word; /* 긴 단어도 줄바꿈 */
  word-wrap: break-word; /* IE11 및 이전 버전과의 호환성을 위해 */
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
  const [showLoginModal, setShowLoginModal] = useState(false);
  const { isLogin } = useLoginStore();
  const [likeCheck, setLikeCheck] = useState(data.likeCheck);
  const [likeCnt, setLikeCnt] = useState(data.likeCnt);

  const { imgUrl, herbName, userNickname, userImgUrl, createAt } = data;

  const favoriteHandler = () => {
    if (!isLogin) {
      setShowLoginModal(true);
      return;
    }

    const config = configService.loginConfig();

    boardService
      .putFavorite(data.boardId, config)
      .then((response) => {
        setLikeCheck(response.flag);
        if (response.flag) {
          setLikeCnt(likeCnt + 1); // likeCnt를 증가시킴
        } else {
          setLikeCnt(likeCnt - 1);
        }
      })
      .catch((error) => {
        console.error(error);
      });
  };

  // 사용자가 좋아요를 눌렀는지 여부에 따라 아이콘을 조정합니다.
  const HeartIcon = likeCheck ? StyledHeartIcon : StyledEmptyHeartIcon;

  return (
    <CardContainer>
      <ImageContainer>
      <HerbImage src={imgUrl} alt={herbName} />
      </ImageContainer>
      <UserSection>
        <UserAvatar src={userImgUrl} alt={userNickname} />
        <UserInfo>
          <Typography sx={{ p: 0, m: 0 }}>{userNickname}</Typography>
          <Typography sx={{ p: 0, m: 0 }}>{createAt}</Typography>
        </UserInfo>
        <LikeCounter>
          <HeartIcon liked={likeCheck} onClick={favoriteHandler} />
          <Typography sx={{ p: 0, m: 0 }}>{likeCnt}</Typography>
        </LikeCounter>
      </UserSection>
      {showLoginModal && (
        <LoginModal
          isOpen={showLoginModal}
          onClose={() => setShowLoginModal(false)}
          redirectUri={"/board"}
        />
      )}
    </CardContainer>
  );
};

export default BoardListItem;
