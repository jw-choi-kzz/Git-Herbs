import styled from 'styled-components';
import { BiEdit, BiCaretUp } from "react-icons/bi";
import { useState, useEffect, useRef } from "react";
import { useNavigate } from "react-router-dom";

import PhotoSizeSelectActualIcon from "@mui/icons-material/PhotoSizeSelectActual";

const ProfileContainer = styled.div`
  display: flex;
  align-items: center;
  justify-content: flex-start; // 중앙 정렬
  position: relative; // 상대적 위치
`;

const ProfileImgContainer = styled.div`
  flex-shrink: 0;   
  position: relative;
  // border-radius: 50%;
  width: 120px;
  height: 120px;
  overflow: hidden;
`;

const ProfileImage = styled.img`
  width: 100%;
  height: 100%;
  border-radius: 50%;  
`;

const ModifyImgButton = styled.button`
  position: absolute;
  right: 0;
  bottom: 0;
  border-radius: 50%;
  background-color: #D9D9D9;
  border: none;
  cursor: pointer;
  padding: 5px;
`;


const NicknameText = styled.span`
  margin-right: 8px;
  font-size: 18px;
`;

const RankingText = styled.div`
  color: #666;
  font-size: 14px;
  border-radius: 10%;
  padding: 2px 6px;
  border: 18px;
  color: #111111;
  background-color: #D9D9D9;
`;

const NicknameAndEditContainer = styled.div`
  display: flex;
  align-items: flex-start; 
  margin-left: 20px; 
  flex-wrap: wrap;
  gap: 8px 0;
`;

const MyProfile = ({ nickname, profileImg, rank }) => {
  const [previewImage, setPreviewImage] = useState();
  const [validNickname, setValidNickname] = useState(true);
  const [nicknameLength, setNicknameLength] = useState();
  const [isDeleting, setIsDeleting] = useState(false);
  const imgRef = useRef();
  const nicknameDialogRef = useRef();
  const profileImageDialogRef = useRef();
  const navigate = useNavigate();

  return (
    <>
    <ProfileContainer>
      <ProfileImgContainer>
        <ProfileImage src={profileImg} alt="Profile" />
        <ModifyImgButton><BiEdit size="1.5em" /></ModifyImgButton>
      </ProfileImgContainer>
      <NicknameAndEditContainer>
        <NicknameText className='medium'>{nickname}</NicknameText>
        <BiEdit size="1.5em" />
        <RankingText><BiCaretUp />상위 {rank}%</RankingText>
      </NicknameAndEditContainer>
    </ProfileContainer>
    </>
  );
};

export default MyProfile;
