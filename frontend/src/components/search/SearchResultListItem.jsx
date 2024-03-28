import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';

// 기존의 ResultContainer를 수정하여 카드 형태로 만듭니다.
const ResultContainer = styled.div`
  display: flex; // 항목들을 가로로 배치하기 위해 flex 설정
  flex-direction: column; // 세로로 나열
  padding: 16px; // 패딩 추가
  // border: 1px solid #d7d7d7; // 테두리 추가
  // border-radius: 8px; // 모서리 둥글게
  margin: 0px 14px; // 아래쪽 마진 추가
`;

const HerbDetails = styled.div`
  display: flex;
  flex-direction: row; // 상세 내용을 가로로 나열
  align-items: center; // 세로 중앙 정렬
`;

const HerbTextDetails = styled.div`
  margin-left: 24px; 
`;

// 이미지 스타일을 그대로 유지합니다.
const HerbImage = styled.img`
  width: 80px;
  height: 80px;
  border-radius: 10px;
`;

const HerbProfileText = styled.div`
  font-size: 12px; 
`;

// 제목과 학명의 스타일을 조금 조정합니다.
const HerbName = styled.h1`
  font-size: 18px; 
  margin: 0; 
`;

const HerbScientificName = styled.h2`
  font-size: 14px; 
  color: #757575;
  margin: 0; 
`;

const HerbMedicalPart = styled.div`
  font-size: 14px; 
  background-color: #F6F7CC;
  padding: 4px 8px; 
  border-radius: 4px; 
  display: inline-block; 
  margin-top: 8px; 
`;


const HerbDescription = styled.div`
  font-size: 14px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  max-width: 340px;
`;

const SearchResultListItem = ({ searchResult }) => { //구조 분해 할당
  const navigate = useNavigate();

  const handleClick = () => {
    navigate(`/detail/1`); //${searchResult.herbId}
  };
  return (
    <ResultContainer onClick={handleClick}>
      <HerbDetails>
        <HerbImage src={searchResult.herbImg} alt={searchResult.herbName}/>
        <HerbTextDetails>
          <HerbName>{searchResult.herbName}</HerbName>
          <HerbScientificName className='regular'>{searchResult.herbScientificName}</HerbScientificName>
          <HerbMedicalPart>{searchResult.herbMedicalPart}</HerbMedicalPart>
        </HerbTextDetails>
      </HerbDetails>
      <br />
      <HerbDescription className='medium'>{searchResult.description}</HerbDescription>
    </ResultContainer>
  );
};

export default SearchResultListItem;
