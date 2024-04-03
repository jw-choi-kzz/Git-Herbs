import { useNavigate } from "react-router-dom";
import styled from "styled-components";

const ResultContainer = styled.div`
  display: flex;
  flex-direction: column;
  padding: 16px;
  // border: 1px solid #d7d7d7;
  // border-radius: 8px;
  margin: 0px 14px;
`;

const HerbDetails = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
`;

const HerbTextDetails = styled.div`
  margin-left: 24px;
`;

const HerbImage = styled.img`
  width: 80px;
  height: 80px;
  border-radius: 10px;
`;

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
  padding: 4px 8px;
  border-radius: 4px;
  display: inline-flex;
  flex-wrap: wrap; 
  margin-top: 8px;
`;

const HerbDescription = styled.div`
  font-size: 14px;
  overflow: hidden;
  text-overflow: ellipsis;
  max-width: 340px;
  b {
    font-size: 16px;
    color: #407700;
    font-weight: bold;
    padding: 0.1em;
  }
`;

const SearchResultListItem = ({ searchResult }) => {
  const navigate = useNavigate();
  const handleClick = (herbId) => {
    navigate(`/detail/${herbId}`);
  };

  return (
    <ResultContainer onClick={() => handleClick(searchResult.id)}>
      <HerbDetails>
        <HerbImage src={searchResult.herbImg} alt={searchResult.herbName} />
        <HerbTextDetails>
          <HerbName className="bold">{searchResult.herbName}</HerbName>
          <HerbScientificName className="regular">
            {searchResult.scientificName}
          </HerbScientificName>
          <HerbMedicalPart className="regular">
            {searchResult.medicalPart}
          </HerbMedicalPart>
        </HerbTextDetails>
      </HerbDetails>
      <br />
      <HerbDescription
        className="medium"
        dangerouslySetInnerHTML={{ __html: searchResult.description }}
      />
    </ResultContainer>
  );
};

export default SearchResultListItem;
