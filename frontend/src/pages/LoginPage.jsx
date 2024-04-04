import { useEffect, useState, useContext } from "react";
import { useNavigate } from "react-router-dom";
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

const LoginPage = () => {
    return (
        <>
            <p>LoginPage</p>
        </>
    )
}

export default LoginPage;