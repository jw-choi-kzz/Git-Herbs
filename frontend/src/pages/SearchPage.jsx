import { useEffect, useState, useContext } from "react";
import SearchBar from '../components/search/SearchBar';
import RecentSearch from '../components/search/RecentSearch';
import RecommendedHerbs from '../components/search/RecommendedHerbs';
import { useNavigate } from "react-router-dom";
import styled from 'styled-components';

const PageContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  width: 100%;
  // height: 100vh;
  margin: 0 auto;
`;

const SearchPage = () => {
    return (
        // <div className="search-page" style={{
        //     display: 'flex',
        //     flexDirection: 'column',
        //     justifyContent: 'flex-start',
        //     alignItems: 'center',
        //     width: '100%',
        //     maxWidth: '375px',
        //     margin: '0 auto',
        // }}>
        <PageContainer>
      <SearchBar />
      <RecentSearch />
      <br />
      <RecommendedHerbs />
      </PageContainer>
    )
}

export default SearchPage;