import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import MonthGrass from "../components/mypage/MonthGrass";
import styled from "styled-components";
import MyProfile from "../components/mypage/MyProfile";
import { BiCalendar, BiTrophy, BiChevronRight } from "react-icons/bi";
import useGlobalStyles from "../utils/useGlobalStyles";
import { configService } from "../apis/config";
import { userServcie } from "../apis/user";
import useLoginStore from "../store/useLoginStore";

const Container = styled.div`
  padding: 24px;
`;

const MenuTitle = styled.div`
  color: #21351f;
  font-size: 20px;
`;

const Explain = styled.div`
  color: #979797;
  font-size: 12px;
  padding: 10px 0;
`;

const NaviContainer = styled.div`
  background-color: #fff;
  border-radius: 14px;
  padding: 10px 20px;
  cursor: pointer;
`;

const IconAndTitle = styled.div`
  display: flex;
`;

//response.data: [{"userId" : int, "userNickname" : string, "userImgurl" : string, "rank" : int, "grass" : [{"date" : date, "count" : int},]}]}

const MyPage = () => {
  useGlobalStyles();
  // const [userData, setUserData] = useState([]);
  const [userInfo, setUserInfo] = useState({});
  const [userGrass, setUserGrass] = useState([]);

  useEffect(() => {
    userServcie.getUserInfo(configService.loginConfig)
      .then((response) => {
        setUserInfo({
          ...response,
          userGrass: response.grass,
        });
      })
      .catch((error) => {
        setError("사용자 정보를 불러오는 데 실패했습니다.");
        console.error(error);
      })
  }, []);

  const navigate = useNavigate();
  const navigateToBadgeList = () => {
    navigate("/mypage/bedge");
  };

  return (
    <div style={{ justifyContent: "center" }}>
      <Container>
        <MyProfile
          nickname={userInfo.userNickname}
          profileImg={userInfo.userImgurl}
        />
        <br />
        <MenuTitle className="bold">
          <BiCalendar style={{ color: "#21351F", fontSize: "24px" }} /> 나의
          잔디 기록
        </MenuTitle>
        <Explain>열심히 Git Herbs를 사용할수록 꽃이 피어나요!</Explain>
        <MonthGrass grassList={userInfo.userGrass} />
        <br />
        <NaviContainer onClick={navigateToBadgeList}>
          <IconAndTitle>
            <BiTrophy style={{ color: "#21351F", fontSize: "24px" }} />
            <MenuTitle className="bold">획득한 뱃지 목록</MenuTitle>
            <BiChevronRight style={{ color: "#979797", fontSize: "24px" }} />
          </IconAndTitle>
          <Explain>활동을 통해 얻은 뱃지를 확인해보세요!</Explain>
        </NaviContainer>
      </Container>
    </div>
  );
};

export default MyPage;
