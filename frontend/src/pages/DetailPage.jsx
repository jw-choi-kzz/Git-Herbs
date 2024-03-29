import React, { useState, useEffect } from 'react';
import store from '../store/store';
import HerbDetailIndex from '../components/herbdetail/HerbDetailIndex';
import HerbProfile from '../components/herbdetail/HerbProfile';
import HerbInfoBox from '../components/herbdetail/HerbInfoBox';
import MyHerbPicture from '../components/herbdetail/MyHerbPicture';
import { useNavigate, useParams } from 'react-router-dom';
import Snackbar from '@mui/joy/Snackbar';
import Typography from '@mui/joy/Typography';
import { herbsService } from '../apis/herbs';
import { configService } from '../apis/config';


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
    herbsService.getHerbInfo(herbId)
    .then(response =>{
      setHerbs(response);
    }).catch(error => {
      console.log(error);
    })
  };

  useEffect(() => {
    fetchHerbDetail(herbId);

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
