import styled from 'styled-components';
import React, { useEffect, useState } from "react";
import HerbList from "../components/pedia/HerbList";
import PediaFilter from '../components/pedia/PediaFilter';
import useStore from '../store/useStore';
import { herbsService } from '../apis/herbs';
import { configService } from '../apis/config';


const PageContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 10px;
  position: relative;
`;

const Title = styled.div`
  width: 100%; /* Take full width */
  font-size: 25px;
  text-align: left;
  color: #4A4A4A;
  margin-top: 10px;
  margin-left: 10px;
  margin-bottom: 10px;
`;

const FilterWrapper = styled.div`
  position: absolute;
  top: 20px; // 상단 여백 설정
  right: 10px; // 우측 여백 설정
  z-index: 10px; // 너비 설정
`;

const PediaPage = () => {
  const { herbs, setHerbs, filterOption, setFilterOption } = useStore();
  // 데이터 상태를 Zustand store에서 가져옵니다.


  
  useEffect(() => {
    getHerb();
  }, []);



  const getHerb = async () => {
    try {
      const loginConfig = await  configService.loginConfig();
      const response = await herbsService.getHerbs(loginConfig);
      setHerbs(response);
    } catch (error) {
      console.log(error);
    }
  };

  const sortHerbs = (herbs, option) => {
    switch (option) {
      case '즐겨찾기 순':
        return [...herbs].sort((a, b) => a.bookmark - b.bookmark || a.herbId - b.herbId);
      case '도감 저장 순':
        return [...herbs].sort((a, b) => a.acquireCheck - b.acquireCheck || a.herbId - b.herbId);
      case '가나다 순':
      default:
        return [...herbs].sort((a, b) => a.herbName.localeCompare(b.herbName));
    }
  };
  

  // 현재 선택된 필터 옵션에 따라 정렬된 약초 데이터
  const sortedHerbs = sortHerbs(herbs, filterOption);

  return (
    <PageContainer>
      <Title>약초 도감</Title>
      <FilterWrapper>
        <PediaFilter filterOption={filterOption} setFilterOption={setFilterOption} />
      </FilterWrapper>
      <HerbList herbsData={sortedHerbs} />
    </PageContainer>
  );
};

export default PediaPage;
