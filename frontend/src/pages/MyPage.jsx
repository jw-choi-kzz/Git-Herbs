import { useEffect, useState, useContext } from "react";
import { useNavigate } from "react-router-dom";
import MonthGrass from "../components/mypage/MonthGrass";
import BedgeList from "../components/mypage/BedgeList";
import styled from "styled-components";
import MyProfile from "../components/mypage/MyProfile";
import { BiCalendar, BiTrophy, BiChevronRight } from "react-icons/bi";
import useGlobalStyles from "../utils/useGlobalStyles";

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
  const navigate = useNavigate();
  const navigateToBadgeList = () => {
    navigate("/mypage/bedge");
  };
  const userData = {
    userId: 1,
    userNickname: "부아아앙",
    userImgurl: "/profileimg/부아아앙.jpg",
    rank: 14,
    grass: [
      {
        date: "2024-03-21",
        count: 2,
      },
      {
        date: "2024-03-22",
        count: 0,
      },
      {
        date: "2024-03-23",
        count: 1,
      },
      {
        date: "2024-03-24",
        count: 3,
      },
    ],
  };
  // overflowY: 'auto', height: 'calc(100vh - 105px)',
  return (
    <div style={{ justifyContent: "center" }}>
      <Container>
        <MyProfile
          nickname={userData.userNickname}
          profileImg={userData.userImgurl}
          rank={userData.rank}
        />
        <br />
        <MenuTitle className="bold">
          <BiCalendar style={{ color: "#21351F", fontSize: "24px" }} /> 나의
          잔디 기록
        </MenuTitle>
        <Explain>열심히 Git Herbs를 사용할수록 꽃이 피어나요!</Explain>
        <MonthGrass grassList={userData.grass} />
        <br />
        <NaviContainer onClick={navigateToBadgeList}>
          <IconAndTitle>
            <BiTrophy style={{ color: "#21351F", fontSize: "24px" }} />{" "}
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
