import React, { useState,useEffect } from 'react';
import styled from 'styled-components';
import { manuualService } from '../../apis/manual';


const Monthtip = () => {
  const [res, setTip] = useState(null);


  useEffect(() => {
    getTip();
  }, []);


  const getTip = async () => {
    manuualService.getTip()
    .then(resposne => {
      setTip(resposne.content);
    })
    .catch(error => {
      console.log(error);
    })
  }

  return (
    <Container>
      <Div3> {res}</Div3>
    </Container>
  );
};
export default Monthtip;



const Container = styled.div`
  width: 320px;
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



const Div3 = styled.div`
  font-family: Spoqa Han Sans Neo, sans-serif;
  flex-grow: 1;
  flex-basis: auto;
  margin: auto 0;
`;
