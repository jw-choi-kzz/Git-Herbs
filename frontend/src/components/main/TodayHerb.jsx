import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import { eventService } from '../../apis/event';

const Container = styled.div`
  width: 320px;
  height: auto; 
  margin: auto;
  border-radius: 10px;
  padding: 20px;
  box-sizing: border-box;
  background-color: #fff;
  box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
  display: flex;
  flex-direction: column;
  align-items: flex-start;
`;

const Title = styled.div`
  width: 100%;
  font-size: 1.25em;
  text-align: left;
  color: #21351F;
  margin-bottom: 10px;
`;

const DetailsContainer = styled.div`
  display: flex;
  align-items: flex-start;
  justify-content: space-between;
`;

const TextContainer = styled.div`
  display: flex;
  flex-direction: column;
`;

const HerbName = styled.div`
  font-size: 1.25em;
  color: #21351F;
  margin: 0 10px;
`;

const SubTitle = styled.div`
  font-size: 0.85em;
  color: #757575;
  margin: 10px 10px;
`;

const HerbImage = styled.img`
  width: 120px;
  height: 120px;
  object-fit: cover;
  margin-right: 10px; 
`;

const HerbDetailButton = styled.button`
cursor: pointer;
padding: 10px;
background-color: #407700;
color: white;
border: none;
border-radius: 5px;
font-size: 14px;
margin: 10px; 
transition: background-color 0.3s ease;
&:hover {
  background-color: #21351F; 
}
`;

const TodayHerb = () => {
  const navigate = useNavigate();

  const [herbId, setHerbId] = useState();
  const [herbName, setHerbName] = useState();
  const [herbImg, setHerbImg] = useState();
  const [herbMedicalPart, setHerbMedicalPart] = useState();



  useEffect(() => {
    const getData = async () => {
      const data = await eventService.getDaily();
      setHerbId(data.herbId);
      setHerbName(data.herbName)
      setHerbImg(data.herbImg)
      setHerbMedicalPart(data.herbMedicalPart)
    }
    getData();
  }, []);

  const handleClick = () => {
    navigate(`/detail/${herbId}`); 
  };

  return (
    <>
      <Container>
        <Title className='bold'>오늘의 약초</Title>
        <DetailsContainer>
          <HerbImage src={herbImg} alt={herbName} />
          <TextContainer>
            <HerbName className='medium'>{herbName}</HerbName>
            <SubTitle className='regular'>약용부위 : {herbMedicalPart}</SubTitle>
            <HerbDetailButton onClick={handleClick}>자세히 보러가기</HerbDetailButton>
          </TextContainer>
        </DetailsContainer>
      </Container>
    </>
  );
}

export default TodayHerb;
