import styled from 'styled-components';

const Container = styled.div`
  width: 375px;
  height: 192px;
  background-color: #fff;
  // display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center; 
`;

const Title = styled.div`
  font-weight: bold;
`;

const RecentSearch = () => {
  return (
    <Container>
      <Title>최근 검색어</Title>
      <br/>
      <p>최근 검색어 약초</p>
    </Container>
  );
};

export default RecentSearch;