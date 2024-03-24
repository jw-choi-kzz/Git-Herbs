import React, { useEffect } from 'react';
import useStore from '../store/store';
import HerbDetailIndex from '../components/herbdetail/HerbDetailIndex';
import HerbProfile from '../components/herbdetail/HerbProfile';
import HerbInfoBox from '../components/herbdetail/HerbInfoBox';
import { useNavigate, useParams } from 'react-router-dom';
import Tabs from '@mui/joy/Tabs';
import TabList from '@mui/joy/TabList';
import Tab from '@mui/joy/Tab';
import TabPanel from '@mui/joy/TabPanel';
import Snackbar from '@mui/joy/Snackbar';
import Button from '@mui/joy/Button';
import Typography from '@mui/joy/Typography';

const DetailPage = ({ isLoggedIn }) => {
  const navigate = useNavigate();
  const { herbId } = useParams();
  const { herbs, setHerbs } = useStore();
  const [selectedTab, setSelectedTab] = React.useState(0);
  const [openSnackbar, setOpenSnackbar] = React.useState(false);

  useEffect(() => {
    // 여기서 임시 데이터를 상태에 설정합니다.
    fetchHerbDetail(herbId);
    // 실제 애플리케이션에서는 여기에서 API 호출을 할 수 있습니다.
    const tempData = {
      herbId: 1,
      herbBookmark: 2,
      herbName: "당귀",
      herbImg: "/herbs/001_00000176_root.jpg",
      herbScientificName: "Angelicae Gigantis Radix",
      herbNickname: "참당귀",
      herbMedicalPart: "뿌리",
      herbHarvestingTime: "1월",
      herbEnvironment: "맑음",
      herbQuality: "말꼬리 모양이 좋다",
      medicinalEffects: [
          {
            "medicinalEffect": "머리가 맑아진다."
          },
          {
            "medicinalEffect": "속이 편안해진다"
          }
          ]
    };
    setHerbs(tempData);
  }, [setHerbs]);

  // 즐겨찾기 버튼 클릭 핸들러
  const handleBookmarkClick = () => {
    if (!isLoggedIn) {
      setOpenSnackbar(true); // 로그인이 필요한 경우 모달창을 엽니다.
    } else {
      // 로그인된 경우 즐겨찾기 로직을 수행합니다.
    }
  };

  // '내가 찍은 사진' 탭 클릭 핸들러
  const handleMyHerbsClick = () => {
    if (!isLoggedIn) {
      setOpenSnackbar(true); // 로그인이 필요한 경우 모달창을 엽니다.
    } else {
      // 로그인된 경우 내가 찍은 사진을 표시합니다.
    }
  };

  return (
    <React.Fragment>
      <Tabs aria-label="Herb details tabs" value={selectedTab} onChange={handleTabChange}>
      <HerbDetailIndex 
        isLoggedIn={isLoggedIn}
        handleBookmarkClick={handleBookmarkClick}
        handleMyHerbsClick={handleMyHerbsClick}
      />
        <TabPanel value={0}>
          {/* 여기에 식물의 상세 설명을 표시하는 컴포넌트 */}
        </TabPanel>
        <TabPanel value={1}>
          {/* 로그인된 사용자를 위한 내가 찍은 이미지를 표시하는 컴포넌트 */}
        </TabPanel>
      </Tabs>
      <Snackbar
        open={openSnackbar}
        onClose={() => setOpenSnackbar(false)}
        anchorOrigin={{ vertical: 'bottom', horizontal: 'center' }}
        sx={{
          '& .MuiSnackbarContent-root': {
            backgroundColor: 'primary.dark', // 여기에서 색상을 정의할 수 있습니다.
          },
        }}
      >
        <Typography>
          로그인이 필요한 서비스입니다.
        </Typography>
      </Snackbar>
    </React.Fragment>
  );
};

export default DetailPage;
