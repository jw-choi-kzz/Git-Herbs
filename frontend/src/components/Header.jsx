import { useNavigate } from 'react-router-dom';
import { HiOutlineSearch } from 'react-icons/hi';
import { BiUserCircle } from 'react-icons/bi';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import IconButton from '@mui/material/IconButton';
import { createTheme, ThemeProvider } from '@mui/material/styles';

const theme = createTheme({
    components: {
      MuiAppBar: {
        styleOverrides: {
          root: {
            backgroundColor: '#f5f5f5', // 이 값으로 헤더의 배경색을 설정합니다.
            // height: '66px',
            boxShadow: 'none',
            borderBottom: '1px solid #d7d7d7',
          },
        },
      },
      MuiToolbar: { // Toolbar의 높이를 AppBar와 동일하게 설정할 필요가 있습니다.
        styleOverrides: {
          root: {
            minHeight: '66px', // AppBar의 높이와 일치하도록 설정
            padding: '0 18px', // 필요한 경우 패딩 제거
            // 이미지나 아이콘의 크기를 조정하여 전체 높이에 맞추기
          },
        },
      },
    },
  });

function Header() {
    const navigate = useNavigate();

    return (
      <ThemeProvider theme={theme}>
          <AppBar position="static">
            <Toolbar disableGutters>
              <Box sx={{ flexGrow: 1, display: 'flex', alignItems: 'center' }}>
                <img
                  src="/logo.svg"
                  alt="Git Herbs Logo"
                  className="h-12 cursor-pointer"
                  onClick={() => navigate('/')}
                />
              </Box>
              <Box>
                <IconButton
                  edge="end"
                  color="inherit"
                  aria-label="search"
                  onClick={() => navigate('/search')}
                >
                  <HiOutlineSearch style={{ fontSize: '2.25rem', color: '#407700'}} />
                </IconButton>
                <IconButton
                  edge="end"
                  color="inherit"
                  aria-label="user account"
                  onClick={() => navigate('/mypage')}
                >
                  <BiUserCircle style={{ fontSize: '2.25rem', color: '#407700'}} />
                </IconButton>
              </Box>
            </Toolbar>
          </AppBar>
      </ThemeProvider>
    );
}

export default Header;
