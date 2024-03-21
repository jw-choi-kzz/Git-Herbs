
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
    components: {
      MuiBottomNavigationAction: {
        styleOverrides: {
          label: {
            // 라벨의 글씨 크기를 설정합니다.
            fontSize: '0.75rem', // 예시 크기입니다. 필요에 따라 조정하세요.
          },
        },
      },
    },
    palette: {
      primary: { main: '#407700' }, // 여기서 primary 색상을 변경합니다
    },
  });

  return (
    <ThemeProvider theme={theme}>
      <div style={{ width: '375px', borderTop: '0.3px solid', borderColor: '#D9D9D9'}}>
        <BottomNavigation
          value={value}
          onChange={(event, newValue) => {
            setValue(newValue);
            navigate(newValue);
          }}
          showLabels
          sx={{
            width: "375px", 
            height: "65px" , 
            bottom:0,     
            borderTop: '1px solid',
            borderColor: 'divider',
            '& .Mui-selected': {
              color: 'primary.main',
              '& .MuiBottomNavigationAction-label': {
                whiteSpace: 'nowrap', // 줄바꿈 방지
                fontSize: '0.75rem',
              },
            },
            '& .MuiBottomNavigationAction-root': {
              color: '#A5A5A5',
              minWidth: 0,
              maxWidth: '20%',
            },
          }}
        >
          <BottomNavigationAction
          label="홈" className='regular'
          value="/"
          icon={<BiHome fontSize='30px' />}
        />
        <BottomNavigationAction
          label="위기탈출" className='regular'
          value="/escape"
          icon={<BiDizzy fontSize='30px'  />}
        />
        <BottomNavigationAction
          label="약초판별" className='regular'
          value="/picture"
          icon={<BiCamera fontSize='30px'  />}
        />
        <BottomNavigationAction
          label="도감" className='regular'
          value="/pedia"
          icon={<BiBookBookmark fontSize='30px' />}
        />
        <BottomNavigationAction
          label="심봤다" className='regular'
          value="/board"
          icon={<BiGame fontSize='30px'  />}
        />
        </BottomNavigation>
      </div>
    </ThemeProvider>
  );
}

export default Footer;
