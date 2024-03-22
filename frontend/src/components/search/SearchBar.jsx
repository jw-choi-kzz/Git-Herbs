import { useNavigate } from 'react-router-dom';
import { HiOutlineSearch } from 'react-icons/hi';
import OutlinedInput from '@mui/material/OutlinedInput';
import IconButton from '@mui/material/IconButton';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import styled from 'styled-components';
import useSearchStore from '../../store/searchStore'; 

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
  width: 320px; 
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
  const { keyword, setKeyword } = useSearchStore();

  const handleSearch = () => {
    if (keyword.trim() !== "") {
      navigate('/search/result');
    }
  }

  return (
    <ThemeProvider theme={theme}>
    <SearchContainer>
      <OutlinedInput
        placeholder="검색어를 입력하세요."
        value={keyword}
        onChange={(e) => setKeyword(e.target.value)}
        endAdornment={
          <IconButton
            edge="end"
            aria-label="search"
            onClick={handleSearch}
          >
            <HiOutlineSearch style={{ color: '#407700' }} />
          </IconButton>
        }
        style={{
          width: '280px',
          height: '43px',
        }}
      />
    </SearchContainer>
  </ThemeProvider>

  );
};

export default SearchBar;