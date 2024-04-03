import styled from 'styled-components';
import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { searchService } from '../../apis/search';
import { configService } from '../../apis/config';

const Container = styled.div`
  display: flex;
  flex-direction: column; 
  align-items: center; 
  background-color: #fff;
  padding: 16px;
  box-sizing: border-box;
`;

const BoldText = styled.div`
  font-weight: bold;
  margin-bottom: 16px;
`;

const HerbsContainer = styled.div`
  display: flex; 
  justify-content: space-around; 
  width: 100%;
`;

const Herb = styled.div`
  display: flex;
  flex-direction: column; 
  align-items: center;
  cursor: pointer;
`;

const HerbImage = styled.img`
  width: 80px; // 이미지 크기를 설정합니다.
  height: 80px; // 이미지 크기를 설정합니다.
  border-radius: 10px; // 이미지에 둥근 모서리를 추가합니다.
  margin-bottom: 8px; // 이미지와 텍스트 사이 공간을 추가합니다.
`;

const HerbName = styled.div`
  font-size: 0.9em; // 이름의 글자 크기를 설정합니다.
  text-align: center;
`;


const RecommendedHerbs = () => {
  const navigate = useNavigate();
  const [recommendList, setRecommendList] = useState([]);

  useEffect(()=>{
    const token = localStorage.getItem('accessToken');
    if (token) {
      const loginConfig = configService.loginConfig();
      searchService.searchRecommend(loginConfig)
        .then(response => {
          setRecommendList(response);
        })
        .catch(error => {
          console.error(error);
        });
    }
  },[]);

  const handleHerbClick = (id) => {
    navigate(`/detail/${id}`);
  };

  return (
    <Container>
      <BoldText>이런 약초는 어때요?</BoldText>
      {recommendList.length > 0 ? (
        <HerbsContainer>
          {recommendList.map((herb, index) => (
            <Herb key={index} onClick={() => handleHerbClick(herb.herbId)}>
              <HerbImage src={herb.herbUrl} alt={herb.herbName} />
              <HerbName>{herb.herbName}</HerbName>
            </Herb>
          ))}
        </HerbsContainer>
      ) : (
        <div>추천할 약초가 없습니다.</div>
      )}
    </Container>
  );
};

export default RecommendedHerbs;