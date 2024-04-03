import { useEffect, useState } from "react";
import { useNavigate } from 'react-router-dom';
import { HiOutlineSearch } from 'react-icons/hi';
import { BiUserCircle } from 'react-icons/bi';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import IconButton from '@mui/material/IconButton';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import LoginModal from "./LoginModal";
import MySnackbar from "./MySnackbar"; 

const theme = createTheme({
  components: {
    MuiAppBar: {
      styleOverrides: {
        root: {
          // width: '375px',
          // backgroundColor: '#fff',
          // boxShadow: 'none',
          // borderBottom: '1px solid #d7d7d7',
          maxWidth: '375px',
          width: '100%', // 부모 컨테이너의 너비에 따라 조정되도록 변경
          margin: '0 auto', // 가운데 정렬을 위해 추가
          backgroundColor: '#fff',
          boxShadow: 'none',
          borderBottom: '1px solid #d7d7d7',
        },
      },
    },
    MuiToolbar: {
      styleOverrides: {
        root: {
          maxHeight: '55px !important',
          minHeight: '55px !important',
          padding: '0 18px', 
        },
      },
    },
  },
});


function Header() {
    const navigate = useNavigate();
    const [showLoginModal, setShowLoginModal] = useState(false);

    const handleClick = async () => {
      const token = localStorage.getItem("accessToken");
  
      // 만약 이미 로그인이 되어 있으면 마이페이지로 이동
      if (token) {
        console.log("로그인됨");
        navigate("/mypage");
      } else {
        // 로그인이 되어 있지 않고, 로그인 모달이 이미 표시 중이면 아무 것도 하지 않음
        if (showLoginModal) {
          console.log("로그인 모달이 이미 열려있음");
          return;
        }
        // 로그인이 되어 있지 않고, 로그인 모달이 표시되지 않은 경우 로그인 모달을 표시
        console.log("로그인안됨");
        setShowLoginModal(true);
      }
    };

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
                  onClick={handleClick}
                  // onClick={() => navigate('/mypage')}
                >
                  <BiUserCircle style={{ fontSize: '35px', color: '#407700'}} />
                </IconButton>
              </Box>
            </Toolbar>
          </AppBar>
          {showLoginModal && (
        <LoginModal
          isOpen={showLoginModal}
          onClose={() => setShowLoginModal(false)}
          redirectUri={"/mypage"}
        />
      )}
      </ThemeProvider>
    );
}

export default Header;
