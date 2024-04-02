import styled from 'styled-components';
import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { searchService } from '../../apis/search';
import { configService } from '../../apis/config';

const Container = styled.div`
  display: flex;
  flex-direction: column; // 방향을 세로로 설정합니다.
  align-items: center; // 가운데 정렬합니다.
  background-color: #fff;
  padding: 16px;
  box-sizing: border-box;
`;

const BoldText = styled.div`
  font-weight: bold;
  margin-bottom: 16px; // 텍스트 아래 공간을 추가합니다.
`;

const HerbsContainer = styled.div`
  display: flex; // 플렉스 레이아웃을 사용합니다.
  justify-content: space-around; // 약초 항목들 사이에 공간을 동일하게 배분합니다.
  width: 100%; // 부모 컨테이너의 전체 너비를 사용합니다.
`;

const Herb = styled.div`
  display: flex;
  flex-direction: column; // 방향을 세로로 설정합니다.
  align-items: center; // 가운데 정렬합니다.
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
  //{"herbId": "...","herbUrl": "...","herbName": "..."}
  const [recommendList, setRecommendList] = useState([]);
      // herbId: 1, //약초 id
      // herbUrl: "./herbs/001_00000010_leaf.jpg", //약초 이미지 url
      // herbName: "칡",

  useEffect(()=>{
    const loginConfig = configService.loginConfig();
    console.log("loginConfig");
    console.log(loginConfig);
    searchService.searchRecommend(loginConfig)
    .then(response => {
      console.log("response");
      console.log(response);
      setRecommendList(response);
    })
    .catch(error=>{
      console.error(error);
    })
  },[]);

  const handleHerbClick = (id) => {
    navigate(`/detail/${id}`);//${id}
  };
  return (
    <Container>
      <BoldText>이런 약초는 어때요?</BoldText>
      <HerbsContainer>
        {recommendList.map((herb, index) => (
          <Herb key={index} onClick={()=>handleHerbClick(herb.herbId)}>
            <HerbImage src={herb.herbUrl} alt={herb.herbName} />
            <HerbName>{herb.herbName}</HerbName>
          </Herb>
        ))}
      </HerbsContainer>
    </Container>
  );
};

export default RecommendedHerbs;