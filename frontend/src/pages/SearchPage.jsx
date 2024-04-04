import { useEffect, useState, useContext } from "react";
import SearchBar from '../components/search/SearchBar';
import RecentSearch from '../components/search/RecentSearch';
import RecommendedHerbs from '../components/search/RecommendedHerbs';
import { useNavigate } from "react-router-dom";
import styled from 'styled-components';

const PageContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  width: 100%;
  // height: 100vh;
  margin: 0 auto;
`;

const SearchPage = () => {
  return (
    <PageContainer>
      <SearchBar />
      <RecentSearch />
      <br />
      <RecommendedHerbs />
    </PageContainer>
  )
}

export default SearchPage;