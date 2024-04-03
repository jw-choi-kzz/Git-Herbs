import React from 'react';
import { useNavigate,Link } from 'react-router-dom';
import styled from 'styled-components';
import { PiStarBold, PiStarFill } from "react-icons/pi";


const StyledLink = styled(Link)`
  text-decoration: none; 
`;

const Container = styled.div`
  display: flex; 
  flex-direction: column; 
  align-items: center; 
  cursor: pointer;
  margin: 10px;
`;

const ItemName = styled.div`
  margin-top: 8px; 
  font-size: 16px; 
  color: #4A4A4A;
  text-align: center; 
  max-width: 65px; 
  word-wrap: break-word;
`;

const ItemContainer = styled.div`
  display: flex;
  position: relative; 
  align-items: center;
  justify-content: center;
  padding: 5px;
  background-color: #F8FFEC;
  width: 68px;
  height: 68px;
`;

const ItemBookMark = styled.div`
  position: absolute; 
  top: 1px;
  right: 1px; 
  font-size: 17px; 
`;

const ItemIcon = styled.img`
  width: 55px;
`;

const HerbListItem = ({ herb }) => {
  const navigate = useNavigate();

  const handleClick = () => {
    navigate(`/detail/${herb.herbId}`);
  };

  return (
    <StyledLink to={`/detail/${herb.herbId}`}>
    <Container>
      <ItemContainer>
        <ItemIcon src={herb.acquireCheck > 0 ? "/pediaimg/plant_acquired.svg" : "/pediaimg/plant_isNotAcquired.svg"} />
        {herb.bookmark > 0 ? (
          <ItemBookMark>
            <PiStarFill style={{ color: '#F49349' }} />
          </ItemBookMark>
        ) : <ItemBookMark><PiStarBold style={{ color: '#F49349' }} /></ItemBookMark>}
      </ItemContainer>
      <ItemName>{herb.herbName}</ItemName>
    </Container>
    </StyledLink>
  );
};

export default HerbListItem;