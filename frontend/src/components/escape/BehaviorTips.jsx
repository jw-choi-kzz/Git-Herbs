import React from 'react';
import styled from 'styled-components';

const BehaviorTips = () => {
  return (
    <Container>
      <Div2>
        <Img
          loading="lazy"
          src="https://cdn.builder.io/api/v1/image/assets/TEMP/4b69be98eef4761f18408fe7982085ff74b418293b97f31994e15f5e20f19960?"
        />
        <Div3>기본 요령</Div3>
      </Div2>
      <Div4>- 1단계 : 위급상황을 인식하고 어떻게 행동할지를 결정하는 것(Check)  </Div4>
      <Div4>- 2단계 : 도움을 요청하는 것(Call)    </Div4>
      <Div4>- 3단계 : 응급의료기관에 인계할 때까지 적절한 처치를 하는 것(Care) </Div4>
    </Container>
  );
};
export default BehaviorTips;
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
const tip = styled.div`
  display: flex;
  gap: 11px;
  font-size: 20px;
  color: #407700;
  font-weight: 700;
`;


const Div2 = styled.div`
  display: flex;
  gap: 11px;
  font-size: 20px;
  color: #407700;
  font-weight: 700;
`;

const Img = styled.img`
  aspect-ratio: 1;
  object-fit: auto;
  object-position: center;
  width: 30px;
`;

const Div3 = styled.div`
  font-family: Spoqa Han Sans Neo, sans-serif;
  flex-grow: 1;
  flex-basis: auto;
  margin: auto 0;
`;

const Div4 = styled.div`
  color: #21351f;
  margin-top: 11px;
  font: 500 14px Spoqa Han Sans Neo, -apple-system, Roboto, Helvetica,
    sans-serif;
`;

