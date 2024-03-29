import styled from 'styled-components';
import SearchResultListItem from './SearchResultListItem';
import { searchService } from '../../apis/search';
import { configService } from '../../apis/config';
import { useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';

const Container = styled.div`
  width: 100%; // 너비를 100%로 변경하여 상위 컨테이너의 크기에 맞춥니다.
  background-color: #fff;
`;

const BoldText = styled.div`
  font-weight: bold;
  padding: 16px; // 상단 패딩 추가
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

  // const searchResults = [
  //   {
  //     herbId: 1,
  //     herbImg: "/herbs/001_00000176_root.jpg",
  //     herbName: "당귀",
  //     herbScientificName: "Angelicae Gigantis Radix",
  //     herbMedicalPart: "뿌리",
  //     description: "바람으로 인한 모든 질환, 혈액으로 인한 모든 질환, 모든 허약증을 치료한다."
  //   },
  //   {
  //     herbId: 2,
  //     herbImg: "/herbs/001_00000176_root.jpg",
  //     herbName: "당귀",
  //     herbScientificName: "Angelicae Gigantis Radix",
  //     herbMedicalPart: "뿌리",
  //     description: "바람으로 인한 모든 질환, 혈액으로 인한 모든 질환, 모든 허약증을 치료한다."
  //   },
  //   {
  //     herbId: 3,
  //     herbImg: "/herbs/001_00000176_root.jpg",
  //     herbName: "당귀",
  //     herbScientificName: "Angelicae Gigantis Radix",
  //     herbMedicalPart: "뿌리",
  //     description: "바람으로 인한 모든 질환, 혈액으로 인한 모든 질환, 모든 허약증을 치료한다."
  //   },
  //   {
  //     herbId: 4,
  //     herbImg: "/herbs/001_00000176_root.jpg",
  //     herbName: "당귀",
  //     herbScientificName: "Angelicae Gigantis Radix",
  //     herbMedicalPart: "뿌리",
  //     description: "바람으로 인한 모든 질환, 혈액으로 인한 모든 질환, 모든 허약증을 치료한다."
  //   }
  // ];
  return (
    <Container>
      <BoldText>검색 결과</BoldText>
      {
        datadata.map(result => ( // 배열의 각 항목을 매핑하여 SearchResultListItem으로 렌더링
          <SearchResultListItem key={result.herbId} searchResult={result}/>
        ))
      }
    </Container>
  );

};

export default SearchResultList;