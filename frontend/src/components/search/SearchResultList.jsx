import styled from "styled-components";
import SearchResultListItem from "./SearchResultListItem";
import { searchService } from "../../apis/search";
import { configService } from "../../apis/config";
import { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import { BiErrorCircle } from "react-icons/bi";
import React from "react";

const Container = styled.div`
  width: 100%;
  background-color: #fff;
`;

const BoldText = styled.div`
  font-weight: bold;
  padding: 16px;
`;

const Divider = styled.hr`
  border: none;
  height: 1px;
  background-color: #e8e8e8;
  margin: 0 16px;
`;

const NoResultContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
  color: #adadad;
  margin: 70px;
`;

const NoResultText = styled.p`
  font-weight: regular;
  text-align: center;
  color: #adadad;
  margin-bottom: 48px;
`;

const SearchResultList = () => {
  const [datadata, setResponse] = useState([]);
  const location = useLocation();

  useEffect(() => {
    const queryParams = new URLSearchParams(location.search);
    const keyword = queryParams.get("keyword");

    searchService
      .searchKeyword(keyword)
      .then((response) => {
        console.log("Response Data:", response);
        setResponse(response);
      })
      .catch((error) => {
        console.error(error);
      });
  }, [location.search]);

  return (
    <Container>
      <BoldText>검색 결과</BoldText>
      {datadata.length > 0 ? (
        datadata.map((result, index) => (
          <React.Fragment key={index}>
            <SearchResultListItem searchResult={result} />
            {index !== datadata.length - 1 && <Divider />}
          </React.Fragment>
        ))
      ) : (
        <NoResultContainer>
          <BiErrorCircle size="100" />
          <NoResultText>검색 결과가 없습니다.</NoResultText>
        </NoResultContainer>
      )}
    </Container>
  );
};

export default SearchResultList;
