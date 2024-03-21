import { useEffect, useState, useContext } from "react";
import { useNavigate } from "react-router-dom";
import SearchResultList from "../components/search/SearchResultList";
import SearchBar from "../components/search/SearchBar";

const SearchResultPage = () => {
    return (
        <>
            <SearchBar />
            <SearchResultList />
        </>
    )
}

export default SearchResultPage;