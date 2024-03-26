
import { useState, useEffect } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import { BiHome, BiDizzy, BiCamera, BiBookBookmark, BiGame } from 'react-icons/bi';
import BottomNavigation from '@mui/material/BottomNavigation';
import BottomNavigationAction from '@mui/material/BottomNavigationAction';
import { ThemeProvider, createTheme } from '@mui/material/styles';
import styled from 'styled-components';

const FooterContainer = styled.footer`
  position: fixed;
  bottom: 0;
  width: 100%;
  z-index: 1000; // 다른 요소들 위에 위치하도록 z-index 설정
`;


function Footer() {
  const navigate = useNavigate();
  const location = useLocation();
  const [value, setValue] = useState('/'); 

  useEffect(() => {
    setValue(location.pathname);
  }, [location]);

  // MUI 테마 생성
  const theme = createTheme({
    components: {
      MuiBottomNavigationAction: {
        styleOverrides: {
          label: {
            fontSize: '12px',
            whiteSpace: 'nowrap',
          },
        },
      },
    },
    palette: {
      primary: { main: '#407700' },
    },
  });

  return (
    <ThemeProvider theme={theme}>
      <FooterContainer position="static">
        <BottomNavigation
          value={value}
          onChange={(event, newValue) => {
            setValue(newValue);
            navigate(newValue);
          }}
          showLabels
          sx={{
            width: "375px", 
            height: "50px" , 
            bottom:0,     
            borderTop: '1px solid',
            borderColor: 'divider',
            '& .Mui-selected': {
              color: 'primary.main',
              '& .MuiBottomNavigationAction-label': {
                whiteSpace: 'nowrap', // 줄바꿈 방지
                fontSize: '12px',
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
          icon={<BiHome fontSize='25px' />}
        />
        <BottomNavigationAction
          label="위기탈출" className='regular'
          value="/escape"
          icon={<BiDizzy fontSize='25px'  />}
        />
        <BottomNavigationAction
          label="약초판별" className='regular'
          value="/picture"
          icon={<BiCamera fontSize='25px'  />}
        />
        <BottomNavigationAction
          label="도감" className='regular'
          value="/pedia"
          icon={<BiBookBookmark fontSize='25px' />}
        />
        <BottomNavigationAction
          label="심봤다" className='regular'
          value="/board"
          icon={<BiGame fontSize='25px'  />}
        />
        </BottomNavigation>
        </FooterContainer>
    </ThemeProvider>
  );
}

export default Footer;
