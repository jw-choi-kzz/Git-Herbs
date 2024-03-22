import styled from 'styled-components';
import { useNavigate } from 'react-router-dom';

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
  const RecommendHerbList = [ 
    {
      herbId: 1, //약초 id
      herbUrl: "./herbs/001_00000010_leaf.jpg", //약초 이미지 url
      herbName: "칡",
    },
    {
      herbId: 2,
      herbUrl: "./herbs/001_00000106_flower.jpg",
      herbName: "칡",
    },
    {
      herbId: 3,
      herbUrl: "./herbs/001_00000176_root.jpg",
      herbName: "칡",
    },
] //response.data.imageList

const handleHerbClick = (id) => {
  navigate(`/detail/1`);//${id}
};
  return (
    <Container>
      <BoldText>이런 약초는 어때요?</BoldText>
      <HerbsContainer onClick={()=>handleHerbClick(1)}>
        {RecommendHerbList.map((herb) => (
          <Herb key={herb.herbId}>
            <HerbImage src={herb.herbUrl} alt={herb.herbName} />
            <HerbName>{herb.herbName}</HerbName>
          </Herb>
        ))}
      </HerbsContainer>
    </Container>
  );
};

export default RecommendedHerbs;