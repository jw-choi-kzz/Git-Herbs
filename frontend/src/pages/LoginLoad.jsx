import { useEffect, useRef } from "react";
import { useNavigate, useLocation, useSearchParams } from "react-router-dom";
import axios from "axios";
import useLoginStore from "../store/useLoginStore";
import styled from 'styled-components';
import logoSvg from '/logo.svg';
import iconPng from '/favicon.png';

// 아이콘을 위한 스타일
const Icon = styled.img`
  width: 50px; // 아이콘 크기 조정
  height: 50px; // 아이콘 크기 조정
  margin-right: 10px; // 문구 컨테이너와의 간격
`;

// 로고와 문구를 포함할 컨테이너
const LogoTextContainer = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 20px; // 컨테이너 안쪽 여백
`;

// 문구를 위한 스타일
const SloganText = styled.h1`
  font-size: 22px;
  color: #333; // 문구 색상 조정
  margin: 0; // 기본 마진 제거
`;

// 로고 이미지를 위한 스타일
const LogoImage = styled.img`
  width: 100px; // 로고 크기 조정
  height: auto; // 로고 높이는 비율에 맞춰 조정
`;

// LoadingContainer 안에 배치
const LoadingContainer = styled.div`
  max-width: 375px;
  width: 100%;
  height: 100%;
  margin: 0 auto;
  background-color: #F8FFEC;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

const Button = styled.button`
  cursor: pointer;
  padding: 10px 20px;
  background-color: transparent;
  color: #979797;
  border: none;
  border-radius: 0; 
  font-size: 12px;
  margin: 0;
  transition: color 0.3s ease;

  &:hover {
    color: #979797; /* Lighter shade for hover state */
  }
`;

const LoginLoad = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const initialRender = useRef(true);
  const [searchParams] = useSearchParams();
  const url = "https://j10a205.p.ssafy.io/api/v1/user/token";
  const { setLogin, setUserId, setProfileImg, setAccessToken, setRefreshToken } = useLoginStore();

  const data = {
    code: searchParams.get("code"),
    error: searchParams.get("error"),
    error_description: searchParams.get("error_description"),
    state: searchParams.get("state"),
  }
  console.log(data);

  const loginProcess = () => {
    axios.post(url, data)
      .then(response => {
        console.log("Login Response:", response);
        const { accessToken, refreshToken, userId, profileImg, redirectUri } = response.data.data;
        
        localStorage.setItem("userId", userId);
        localStorage.setItem("profileImg", profileImg);
        localStorage.setItem("accessToken", accessToken);
        localStorage.setItem("refreshToken", refreshToken);

        let reUrl = "/"
        if(redirectUri!=null &&redirectUri!=""){
          reUrl = redirectUri;
        }

        setLogin(true);
        setUserId(userId);
        setProfileImg(profileImg);
        setAccessToken(accessToken);
        setRefreshToken(refreshToken);
        navigate(reUrl);
      })
      .catch(error => {
        console.error("Login Error:", error);
      });
  };

  useEffect(() => {
    if (initialRender.current) {
      initialRender.current = false;
      loginProcess();
    }
  }, [navigate]);

  return (
    <LoadingContainer>
      <LogoTextContainer>
        <Icon src={iconPng} alt="Icon" />
        <div>
          <SloganText className='regular'>나만의 약초 도감</SloganText>
          <LogoImage src={logoSvg} alt="Gift Herbs Logo" />
        </div>
      </LogoTextContainer>
      <Button onClick={()=>navigate("/")}>홈으로이동</Button>
    </LoadingContainer>
  );
};

export default LoginLoad;
