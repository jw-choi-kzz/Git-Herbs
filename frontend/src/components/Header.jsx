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
          backgroundColor: '#fff',
          boxShadow: 'none',
          borderBottom: '1px solid #d7d7d7',
        },
      },
    },
    MuiToolbar: {
      styleOverrides: {
        root: {
          maxHeight: '50px !important',
          minHeight: '50px !important',
          padding: '0 18px', 
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
                  style={{ userSelect: 'none' }} 
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
                  <HiOutlineSearch style={{ fontSize: '35px', color: '#407700'}} />
                </IconButton>
                <IconButton
                  edge="end"
                  color="inherit"
                  aria-label="user account"
                  onClick={() => navigate('/mypage')}
                >
                  <BiUserCircle style={{ fontSize: '35px', color: '#407700'}} />
                </IconButton>
              </Box>
            </Toolbar>
          </AppBar>
      </ThemeProvider>
    );
}

export default Header;
