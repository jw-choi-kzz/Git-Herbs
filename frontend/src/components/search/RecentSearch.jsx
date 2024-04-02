import { useState, useEffect } from 'react';
import styled from 'styled-components';
import { useNavigate } from 'react-router-dom';
import { BiTime } from "react-icons/bi";
import { searchService } from '../../apis/search';
import { configService } from '../../apis/config';

const Container = styled.div`
  width: 100%;
  height: 192px;
  background-color: #fff;
  padding: 16px;
  box-sizing: border-box;
  flex-direction: row;
  align-items: center;
  justify-content: center; 
`;

const Title = styled.div`
  font-weight: bold;
`;

const KeywordContainer = styled.div`
  display: flex; // 플렉스로 설정
  align-items: center; // 세로 정렬
  margin: 10px; // 각 키워드 아이템 아래 마진 추가
  cursor: pointer;
`;

const Icon = styled(BiTime)`
  color: #979797;
  font-size: 20px;
  margin-right: 8px; // 아이콘과 텍스트 사이 마진 추가
`;

const Keyword = styled.div`
  font-size: 20px; // 크기를 적절하게 조절
  font-weight: medium;
`;

const LoginPrompt = styled.div`
  font-size: 16px; // 크기를 적절하게 조절
  color: #6c757d; // 색상을 조절
  text-align: center; // 가운데 정렬
`;

const RecentSearch = () => {
  const navigate = useNavigate();
  const [recentKeywords, setRecentKeywords] = useState([]);

  useEffect(()=>{
      const loginConfig = configService.loginConfig();
      searchService.searchRecent(loginConfig)
      .then(response => {
        setRecentKeywords(response);
        console.log("response");
        console.log(response);
      })
      .catch(error=>{
        console.error(error);
      });
  },[]);

  const handleKeywordClick = (searchQuery) => {
    navigate(`/search/result?keyword=${searchQuery}`);
  };


  return (
    <Container>
    <Title>최근 검색어</Title>
    {recentKeywords.length > 0 ? (
      recentKeywords.map((item, index) => (
        <KeywordContainer key={index} onClick={() => handleKeywordClick(item.keyword)}>
          <Icon />
          <Keyword>{item.keyword}</Keyword>
        </KeywordContainer>
      ))
    ) : (
      <div>최근 검색어가 없습니다.</div>
    )}
  </Container>
  
  );
};

export default RecentSearch;