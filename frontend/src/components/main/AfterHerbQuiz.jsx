import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import styled from 'styled-components';
import { BiMessageCheck } from "react-icons/bi";

const Container = styled.div`
  width: 280px;
  margin: auto;
  border-radius: 10px;
  padding: 20px;
  box-sizing: border-box;
  background-color: #fff;
  box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const Title = styled.div`
  font-size: 1.25em;
  color: #4A4A4A;
  width: 100%;
  text-align: left;
`;

const SubTitle = styled.div`
  font-size: 14px;
  color: #4A4A4A;
  margin: 2px 0;
  width: 100%;
  text-align: center;
`;

const Icon = styled(BiMessageCheck)`
    color: #97C64A;
    font-size: 120px;
`;

const AfterHerbQuiz = () => {
    return (
        <Container>
            <Title className="bold">오늘의 약초 퀴즈</Title>
            <Icon/>
            <SubTitle>퀴즈 정답을 맞췄습니다!</SubTitle>
        </Container>
    )
}

export default AfterHerbQuiz;