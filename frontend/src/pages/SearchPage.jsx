import { useEffect, useState, useContext } from "react";
import SearchBar from '../components/search/SearchBar';
import RecentSearch from '../components/search/RecentSearch';
import RecommendedHerbs from '../components/search/RecommendedHerbs';
import { useNavigate } from "react-router-dom";

const SearchPage = () => {
    return (
        <div className="search-page" style={{
            display: 'flex',
            flexDirection: 'column',
            justifyContent: 'flex-start',
            alignItems: 'center',
            width: '100%',
            maxWidth: '375px',
            margin: '0 auto',
        }}>
      <SearchBar />
      <RecentSearch />
      <br />
      <RecommendedHerbs />
    </div>
    )
}

export default SearchPage;