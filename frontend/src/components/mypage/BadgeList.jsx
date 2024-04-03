import { useEffect, useState } from 'react';
import BadgeListItem from './BadgeListItem';
import styled from 'styled-components';
import { configService } from '../../apis/config';
import { eventService } from "../../apis/event";

const Title = styled.div`
  font-size: 20px;
  padding: 0 0 10px 0;
`;

const Container = styled.div`
  // width: 320px;
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

const Explain = styled.div`
  font-size: 0.85em;
  color: #4A4A4A;
  margin: 10px 0;
  width: 100%;
  text-align: start;
`;

const GridContainer = styled.div`
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 10px; // Space between grid items
  justify-items: center;
  padding: 20px; 
`;

const BadgeList = () => {
  const [badges, setBadges] = useState([]);

  useEffect(()=>{
    const loginConfig = configService.loginConfig();
    eventService.getUserBadge(loginConfig)
    .then(response => {
      setBadges(response || []);
    })
    .catch(error=>{
      console.error(error);
    })
  },[]);

  const acquiredBadgeCount = Array.isArray(badges) ? badges.filter(badge => badge.check).length : 0;

  return (
    <>
      <Title className='bold'>획득한 뱃지 목록</Title>
      <Explain>전체 뱃지 4개 중 {acquiredBadgeCount}개를 획득했어요.</Explain>
      <Container>
        <GridContainer>
          {badges.map((badge, index) => (
            <BadgeListItem key={index} badge={badge} />
          ))}
        </GridContainer>
      </Container>
      
    </>
  );
};

export default BadgeList;