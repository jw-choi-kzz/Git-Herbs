
import { useState, useEffect } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import { BiHome, BiDizzy, BiCamera, BiBookBookmark, BiGame } from 'react-icons/bi';
import BottomNavigation from '@mui/material/BottomNavigation';
import BottomNavigationAction from '@mui/material/BottomNavigationAction';
import { ThemeProvider, createTheme } from '@mui/material/styles';

function Footer() {
  const navigate = useNavigate();
  const location = useLocation();
  const [value, setValue] = useState('/'); // Set the initial route

  useEffect(() => {
    setValue(location.pathname);
  }, [location]);

  // MUI 테마 생성
  const theme = createTheme({
    palette: {
      primary: { main: '#407700' }, // 여기서 primary 색상을 변경합니다
    },
  });

  return (
    <ThemeProvider theme={theme}>
      {/* <div className="flex justify-center w-full h-16 bottom-0 border-t-0.3"> */}
      <div className="fixed bottom-0 w-full">
      <div style={{ maxWidth: '412px', width: '100%' }}>
        <BottomNavigation
          value={value}
          onChange={(event, newValue) => {
            setValue(newValue);
            navigate(newValue);
          }}
          showLabels
          sx={{
            width: "100%", 
            height: "100%" , 
            bottom:0,     
            borderTop: '1px solid',
            borderColor: 'divider',
            '& .Mui-selected': {
              color: 'primary.main',
            },
            '& .MuiBottomNavigationAction-root': {
              color: '#A5A5A5',
              minWidth: 0,
              maxWidth: '20%',
            },
          }}
        >
          <BottomNavigationAction
          label="홈"
          value="/"
          icon={<BiHome fontSize='24px' />}
        />
        <BottomNavigationAction
          label="위기탈출"
          value="/escape"
          icon={<BiDizzy fontSize='24px'  />}
        />
        <BottomNavigationAction
          label="약초판별"
          value="/picture"
          icon={<BiCamera fontSize='24px'  />}
        />
        <BottomNavigationAction
          label="도감"
          value="/pedia"
          icon={<BiBookBookmark fontSize='24px' />}
        />
        <BottomNavigationAction
          label="심봤다"
          value="/board"
          icon={<BiGame fontSize='24px'  />}
        />
        </BottomNavigation>
      </div>
      </div>
    </ThemeProvider>
  );
}

export default Footer;
