import styled from 'styled-components';
import { useNavigate } from 'react-router-dom';

// Styled Components는 컴포넌트 바깥에서 정의해야함.
//컴포넌트 렌더링될 때마다 새로운 Styled Component가 만들어지지 않도록
//성능 저하 방지, 불필요한 리렌더링을 막기 위해
//속성 이름은 camelCase가 아니라 - 사용
const Container = styled.div`
  width: 280px;
  height: auto; /* Changed to auto to wrap content */
  margin: auto;
  border-radius: 10px;
  padding: 20px;
  box-sizing: border-box;
  background-color: #fff;
  box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
  display: flex;
  flex-direction: column;
  align-items: flex-start; /* Align items to start */
  cursor: pointer;
`;

const Title = styled.div`
  width: 100%; /* Take full width */
  font-size: 1.25em;
  text-align: left;
  color: #4A4A4A;
  margin-bottom: 10px; /* Space between title and details */
`;

const DetailsContainer = styled.div`
  display: flex;
  align-items: flex-start; /* Align items to start */
  justify-content: space-between; /* Space between image and text */
`;

const TextContainer = styled.div`
  display: flex;
  flex-direction: column;
`;

const HerbName = styled.div`
  font-size: 1.25em;
  color: #4A4A4A;
  margin: 0 10px;
`;

const SubTitle = styled.div`
  font-size: 0.85em;
  color: #4A4A4A;
  margin: 10px 10px;
`;

const HerbImage = styled.img`
  width: 120px;
  height: 120px;
  object-fit: cover;
  margin-right: 10px; /* Add margin to the right of the image */
`;

const TodayHerb = () => {
    const navigate = useNavigate();

    const todayHerb = {
        herbId: 1,
        herbUrl: "/herbs/001_00000176_root.jpg",
        herbName: "칡",
        latinName: "Paeoniae Radix",
    } //api연결 전 임의 약초 정보

    const handleClick = () => {
        navigate(`/detail/${todayHerb.herbId}`); // 클릭 시 상세 페이지로 이동
    };

    return (
      <>
        <Container onClick={handleClick}>
          <Title className="bold">오늘의 약초</Title>
          <DetailsContainer>
            <HerbImage src={todayHerb.herbUrl} alt={todayHerb.herbName} />
            <TextContainer>
              <HerbName>{todayHerb.herbName}</HerbName>
              <SubTitle>{todayHerb.latinName}</SubTitle>
              {/* If you have more text elements, they will go here */}
            </TextContainer>
          </DetailsContainer>
        </Container>
      </>
    );
}

export default TodayHerb;
