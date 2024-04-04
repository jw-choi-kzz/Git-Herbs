import { useNavigate } from 'react-router-dom';
import { HiOutlineSearch } from 'react-icons/hi';
import OutlinedInput from '@mui/material/OutlinedInput';
import IconButton from '@mui/material/IconButton';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import styled from 'styled-components';
import {searchService} from '../../apis/search'; 
import { useState } from 'react';

const theme = createTheme({
  components: {
    MuiOutlinedInput: {
      styleOverrides: {
        root: {
          backgroundColor: '#F3F3FB', 
          '&.Mui-focused .MuiOutlinedInput-notchedOutline': {
            borderColor: '#407700', // 테두리 색상 변경
          },
          '&:hover .MuiOutlinedInput-notchedOutline': {
            borderColor: '#407700', // 호버 시 테두리 색상 변경
          },
          '& .MuiOutlinedInput-notchedOutline': {
            borderColor: '#d7d7d7', // 기본 테두리 색상
          }
        },
      },
    },
  },
});

const SearchContainer = styled.div`
  margin-top: 0;
  width: 100%; 
  height: 100px; 
  background-color: #fff; 
  display: flex; 
  position: relative; 
  border-bottom: 1px solid #d7d7d7;
  flex-direction: row; 
  align-items: center; 
  justify-content: center; 
`;

const SearchBar = () => {
  const navigate = useNavigate();
  const [keyword, setKeyword] = useState("");

  const handleSearch = async (searchQuery) => {
    navigate(`/search/result?keyword=${searchQuery}`);
  };

  const handleKeyDown = (event) => {
    if (event.key === 'Enter') { // 'Enter' 키를 눌렀을 때만 검색 실행
      handleSearch(keyword); // 수정됨
    }
  };

  return (
    <ThemeProvider theme={theme}>
      <SearchContainer>
        <OutlinedInput
          placeholder="검색어를 입력하세요."
          value={keyword}
          onChange={(e) => setKeyword(e.target.value)}
          onKeyDown={handleKeyDown}
          endAdornment={
            <IconButton
              edge="end"
              aria-label="search"
              onClick={() => handleSearch(keyword)}
            >
              <HiOutlineSearch style={{ color: '#407700' }} />
            </IconButton>
          }
          style={{
            width: '80%',
            height: '40px',
          }}
        />
      </SearchContainer>
    </ThemeProvider>
  );
};

export default SearchBar;