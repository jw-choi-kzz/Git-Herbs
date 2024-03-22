import styled from 'styled-components';
import { useNavigate } from 'react-router-dom';
import { BiTime } from "react-icons/bi";

const Container = styled.div`
  width: 320px;
  height: 192px;
  background-color: #fff;
  padding: 16px;
  box-sizing: border-box;
  flex-direction: row;
  align-items: center;
  justify-content: center; 
`;

const Title = styled.div`
  font-weight: bold;
`;

const KeywordContainer = styled.div`
  display: flex; // 플렉스로 설정
  align-items: center; // 세로 정렬
  margin: 10px; // 각 키워드 아이템 아래 마진 추가
  cursor: pointer;
`;

const Icon = styled(BiTime)`
  color: #979797;
  font-size: 20px;
  margin-right: 8px; // 아이콘과 텍스트 사이 마진 추가
`;

const Keyword = styled.div`
  font-size: 20px; // 크기를 적절하게 조절
  font-weight: medium;
`;
const RecentSearch = () => {
  const navigate = useNavigate();
  
  //response.data에 herbName": string
  const keywordList = [
    {
      id: 1,
      herbName: "칡"
    },
    {
      id: 2,
      herbName: "쑥"
    },
    {
      id: 3,
      herbName: "작약"
    }
  ];

  const handleKeywordClick = (id) => {
    navigate(`/detail/1`);//${id
  };

  return (
    <Container>
      <Title>최근 검색어</Title>
      {keywordList.map((keyword) => (
        <KeywordContainer onClick={() => handleKeywordClick(keyword.id)}>
          <Icon />
          <Keyword>{keyword.herbName}</Keyword>
        </KeywordContainer>
      ))}
    </Container>
  );
};

export default RecentSearch;