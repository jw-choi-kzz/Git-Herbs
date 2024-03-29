import React, { useState, useEffect } from 'react';
import store from '../store/store';
import HerbDetailIndex from '../components/herbdetail/HerbDetailIndex';
import HerbProfile from '../components/herbdetail/HerbProfile';
import HerbInfoBox from '../components/herbdetail/HerbInfoBox';
import MyHerbPicture from '../components/herbdetail/MyHerbPicture';
import { useNavigate, useParams } from 'react-router-dom';
import Snackbar from '@mui/joy/Snackbar';
import Typography from '@mui/joy/Typography';

const DetailPage = () => {
  const navigate = useNavigate();
  const { herbId } = useParams();
  const { herbs, setHerbs } = store();
  const [selectedTab, setSelectedTab] = useState(0);
  const [openSnackbar, setOpenSnackbar] = useState(false);

  const handleTabChange = (event, newValue) => {
    setSelectedTab(newValue);
  };

  const fetchHerbDetail = async (herbId) => {
    const tempData = {
      herbId: 1,
      herbBookmark: 2,
      herbName: "당귀",
      herbImg: "/herbs/001_00000176_root.jpg",
      herbScientificName: "Angelicae Gigantis Radix",
      herbNickname: "진당귀, 금당귀, 천당귀, 건귀, 마미당귀, 귀미, 진귀",
      herbMedicalPart: "뿌리",
      herbHarvestingTime: "음력 2월과 8월",
      herbEnvironment: "산과 들에 저절로 자라기도 하고, 밭에 심기도 한다.",
      herbQuality: "당귀는 육질이 두툼하고 촉촉한 것이 더 좋다. 말꼬리처럼 생긴 것이 더 좋다고도 한다.",
      medicinalEffects: [
        {
          "medicinalEffect": "바람으로 인한 모든 질환, 혈액으로 인한 모든 질환, 모든 허약증을 치료한다. 나쁜 피를 없애고 새 피가 생기게 한다. 몸속에 덩어리가 생긴 것, 여성의 자궁출혈과 불임증을 치료한다. 모든 심한 부스럼과 상처를 치료한다. 나쁜 피가 몸속에 막힌 것을 치료한다. 이질로 인한 복통을 치료한다. 말라리아로 열이 나는 것을 치료한다. 모든 장기를 보한다. 새설이 나게 한다."
        },
        {
          "medicinalEffect": "당귀 뿌리의 머리 부분을 당귀두라고 하는데, 당귀두는 나쁜 피를 없애고 혈액순환을 촉진한다. 당귀 뿌리의 꼬리 부분을 당귀미라고 하는데, 당귀미는 통증을 멈추고 지혈한다."
        },
        {
          "medicinalEffect": "당귀두는 혈액순환을 촉진하고, 당귀미는 지혈한다. 당귀 전체를 함께 사용하면 혈액순환도 촉진하면서 출혈도 지혈하니, 이 작용을 ‘화혈’이라고 부른다."
        }
      ]
    };
    setHerbs(tempData);

    // 실제 서버 요청 코드 (현재는 주석 처리)
    // try {
    //   const response = await axios.get(`https://example.com/herbs/${herbId}`);d
    //   setHerbs(response.data);
    // } catch (error) {
    //   console.error("Herb details fetch error:", error);
    // }
  };

  useEffect(() => {
    // 스크롤바 보이게 설정
    // const customScrollStyle = () => {
    //   document.body.style.overflowY = 'auto';
    //   document.body.style.msOverflowStyle = 'scrollbar';
    //   document.body.style.scrollbarWidth = 'thin';
    //   document.body.style['&::-webkit-scrollbar'] = {
    //     width: '12px',
    //     background: '#f0f0f0',
    //   };
    //   document.body.style['&::-webkit-scrollbar-thumb'] = {
    //     background: '#888',
    //     borderRadius: '6px',
    //   };
    // };

    // 스타일 적용
    // customScrollStyle();

    fetchHerbDetail(herbId);

    // 실제 애플리케이션에서는 여기에서 API 호출을 할 수 있습니다.

    // return () => {
    //   document.body.style.overflowY = '';
    //   document.body.style.msOverflowStyle = '';
    //   document.body.style.scrollbarWidth = '';
    //   document.body.style['&::-webkit-scrollbar'] = {};
    //   document.body.style['&::-webkit-scrollbar-thumb'] = {};
    // };

  }, [herbId, setHerbs]);

  return (
    // 스크롤을 허용하는 컨테이너 overflowY: 'auto', height: 'calc(100vh - 105px)', 
    // <div style={{ justifyContent: 'center' }}>
    <React.Fragment>
      <HerbProfile data={herbs} />
      <HerbDetailIndex
        selectedTab={selectedTab}
        onTabChange={handleTabChange}
      />
      {selectedTab === 0 && <HerbInfoBox data={herbs} />}
      {selectedTab === 1 && <MyHerbPicture herbId={herbId} />}
      <Snackbar
        open={openSnackbar}
        onClose={() => setOpenSnackbar(false)}
        anchorOrigin={{ vertical: 'bottom', horizontal: 'center' }}
      >
        <Typography>
          로그인이 필요한 서비스입니다.
        </Typography>
      </Snackbar>
    </React.Fragment>
    // </div>
  );
};

export default DetailPage;
