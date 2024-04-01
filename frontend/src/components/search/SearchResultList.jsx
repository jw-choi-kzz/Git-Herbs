import styled from 'styled-components';
import SearchResultListItem from './SearchResultListItem';
import { searchService } from '../../apis/search';
import { configService } from '../../apis/config';
import { useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';
import { BiErrorCircle } from 'react-icons/bi'; 

const Container = styled.div`
  width: 100%; // 너비를 100%로 변경하여 상위 컨테이너의 크기에 맞춥니다.
  background-color: #fff;
`;

const BoldText = styled.div`
  font-weight: bold;
  padding: 16px; // 상단 패딩 추가
`;

const NoResultContainer = styled.div`
  display: flex;
  flex-direction: column; 
  align-items: center;
  text-align: center;
  color: #ADADAD;
  margin: 70px; 
`;

const NoResultText = styled.p`
  font-weight: regular;
  text-align: center;
  color: #ADADAD; 
  margin-bottom: 48px; 
`;


const SearchResultList = () => {
  const [datadata , setResponse] = useState([]);
  const location = useLocation(); // 현재 위치 정보를 가져옴
  
  useEffect(() => {
    const queryParams = new URLSearchParams(location.search);
    const keyword = queryParams.get('keyword'); // 'keyword' 파라미터의 값을 가져옴

    searchService.searchKeyword(keyword)
    .then(response =>{
      setResponse(response);
    })
    .catch(error=>{
      console.error(error);
    })
  },[location.search]);

  return (
    <Container>
      <BoldText>검색 결과</BoldText>
      {
        datadata.length > 0 ? (
          datadata.map(result => (
            <SearchResultListItem key={result.herbId} searchResult={result}/>
          ))
        ) : (
          <NoResultContainer>
            <BiErrorCircle size="100" /> 
            <NoResultText>검색 결과가 없습니다.</NoResultText>
          </NoResultContainer>
        )
      }
    </Container>
  );

};

export default SearchResultList;