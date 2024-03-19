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
            boxShadow: 'none',
            borderBottom: '1px solid #d7d7d7',
          },
        },
      },
      // 다른 컴포넌트 스타일 설정이 여기 들어갈 수 있습니다.
    },
  });

function Header() {
    const navigate = useNavigate();

    return (
      <ThemeProvider theme={theme}>
        <Box sx={{ flexGrow: 1 }}>
          <AppBar position="static">
            <Toolbar>
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
        </Box>
      </ThemeProvider>
    );
}

export default Header;
