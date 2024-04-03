import styled from "styled-components";
import React, { useEffect, useState } from "react";
import HerbList from "../components/pedia/HerbList";
import PediaFilter from "../components/pedia/PediaFilter";
import { herbsService } from "../apis/herbs";
import { configService } from "../apis/config";

const PageContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 10px;
  position: relative;
`;

const Title = styled.h2`
  width: 100%;
  text-align: left;
  // color: #4a4a4a;
  margin-top: 10px;
  margin-left: 10px;
  margin-bottom: 10px;
  margin-right: 10px;
`;

const FilterWrapper = styled.div`
  position: absolute;
  top: 27px; // 상단 여백 설정
  right: 20px; // 우측 여백 설정
  z-index: 10px; // 너비 설정
`;

const PediaPage = () => {
  const [herbs, setHerbs] = useState([]);
  const [filterOption, setFilterOption] = useState("가나다 순");
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    const getHerb = async () => {
      setIsLoading(true);
      try {
        const loginConfig = await configService.loginConfig();
        const response = await herbsService.getHerbs(loginConfig);
        setHerbs(response);
      } catch (error) {
        console.log("Herbs 데이터 로딩 에러:", error);
      } finally {
        setIsLoading(false);
      }
    };

    getHerb();
  }, []);

  const sortHerbs = (herbs, option) => {
    switch (option) {
      case "즐겨찾기 순":
        return [...herbs].sort((a, b) => {
          if (a.bookmark !== b.bookmark) {
            return b.bookmark - a.bookmark;
          } else {
            return a.herbName.localeCompare(b.herbName);
          }
        });
      case "도감 저장 순":
        return [...herbs].sort((a, b) => {
          if (a.acquireCheck !== b.acquireCheck) {
            return b.acquireCheck - a.acquireCheck;
          } else {
            return a.herbName.localeCompare(b.herbName);
          }
        });
      case "가나다 순":
      default:
        return [...herbs].sort((a, b) => a.herbName.localeCompare(b.herbName));
    }
  };

  const sortedHerbs = sortHerbs(herbs, filterOption);

  return (
    <PageContainer>
      <Title>약초 도감</Title>
      <FilterWrapper>
        <PediaFilter
          filterOption={filterOption}
          setFilterOption={setFilterOption}
        />
      </FilterWrapper>
      <HerbList herbsData={sortedHerbs} />
    </PageContainer>
  );
};

export default PediaPage;
