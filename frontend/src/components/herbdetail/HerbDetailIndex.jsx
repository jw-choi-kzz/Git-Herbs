import { useState } from "react";
import styled from "styled-components";
import Tabs from "@mui/joy/Tabs";
import TabList from "@mui/joy/TabList";
import Tab from "@mui/joy/Tab";
import TabPanel from "@mui/joy/TabPanel";
import LoginModal from "../LoginModal";
import useLoginStore from "../../store/useLoginStore";

const StyledTabList = styled(TabList)`
  display: flex; // 탭 리스트를 flex 컨테이너로 만들어줌
  background-color: #f5f5f5;
  border-bottom: 1px solid #999;
  .MuiTabs-indicator {
    background-color: #407700;
  }
`;
const StyledTabs = styled(Tabs)`
  background-color: #f5f5f5;
`;

const StyledTab = styled(Tab)`
  flex: 1 !important;
  min-width: 0px;
  &.bold {
    color: #21351f;
  }
  &.Mui-selected {
    color: #407700;
    font-size: 16px;
    font-weight: 700;
    background-color: #f5f5f5 !important;
  }
  &:hover {
    font-weight: 700;
    background-color: #f5f5f5 !important; // 호버 시 탭의 배경색 지정
  }
`;

const StyledTabPanel = styled(TabPanel)`
  background-color: #f5f5f5;
`;
const HerbDetailIndex = ({ selectedTab, onTabChange, herbId }) => {
  const { isLogin } = useLoginStore();
  const [showLoginModal, setShowLoginModal] = useState(false);

  const handleTabChange = (event, newValue) => {
    if (newValue === 1 && !isLogin) {
      // '내가 찍은 이미지' 탭이고 로그인 상태가 아니라면
      setShowLoginModal(true); // 로그인 모달 표시
    } else {
      onTabChange(event, newValue); // 부모 컴포넌트의 탭 변경 처리
    }
  };

  return (
    <>
      <StyledTabs
        aria-label="Herb details"
        value={selectedTab}
        onChange={handleTabChange}
      >
        <StyledTabList>
          <StyledTab className="bold">상세 설명</StyledTab>
          <StyledTab className="bold">내가 찍은 이미지</StyledTab>
        </StyledTabList>
        <StyledTabPanel value={0}></StyledTabPanel>
        <StyledTabPanel value={1}></StyledTabPanel>
      </StyledTabs>
      {showLoginModal && (
        <LoginModal
          isOpen={showLoginModal}
          onClose={() => setShowLoginModal(false)}
          redirectUrl={`/detail/${herbId}`}
        />
      )}
    </>
  );
};

export default HerbDetailIndex;
