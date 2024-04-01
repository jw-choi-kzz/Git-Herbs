import { useEffect, useState } from 'react';
import BedgeListItem from './BedgeListItem';
import styled from 'styled-components';
import { configService } from '../../apis/config';
import { userServcie } from "../../apis/user";

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

const BedgeList = () => {
  const [bedges, setBedges] = useState([]);
  useEffect(()=>{
    userServcie.getUserBedge(configService.loginConfig)
    .then(response => {
      setBedges(response);
    })
    .catch(error=>{
      console.error(error);
    })
    
  },[]);

  return (
    <>
      <Title className='bold'>획득한 뱃지 목록</Title>
      <Explain>전체 뱃지 8개 중 {bedges.filter(bedge => bedge.check).length}개를 획득했어요.</Explain>
      <Container>
        <GridContainer>
          {bedges.map((bedge) => (
            <BedgeListItem key={bedge.badgeId} {...bedge} />
          ))}
        </GridContainer>
      </Container>
      
    </>
  );
};

export default BedgeList;