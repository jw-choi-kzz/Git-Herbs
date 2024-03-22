import styled from 'styled-components';

const Container = styled.div`
  width: 320px;
  height: 220px;
  background-color: #fff;
  display: flex;
  flex-direction: row;
`;

const BoldText = styled.div`
  font-weight: bold;
`;

const RecommendedHerbs = () => {
  return (
    <Container>
      <BoldText>이런 약초는 어때요?</BoldText>
    </Container>
  );
};

export default RecommendedHerbs;