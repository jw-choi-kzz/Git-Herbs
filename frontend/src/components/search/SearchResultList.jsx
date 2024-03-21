import styled from 'styled-components';
import SearchResultListItem from './SearchResultListItem';

const Container = styled.div`
  margin-top: 0;
  width: 375px;
  height: auto;
  background-color: #fff;
  // display: flex;
  position: relative;
  flex-direction: row;
  align-items: center;
  justify-content: center;
`;

const BoldText = styled.div`
  font-weight: bold;
`;

const SearchResultList = () => {
  return (
    <Container>
      <BoldText>검색 결과</BoldText>
      <SearchResultListItem />
    </Container>
  );
};

export default SearchResultList;