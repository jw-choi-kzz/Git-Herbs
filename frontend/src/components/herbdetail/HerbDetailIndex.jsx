import React from 'react';
import Tabs from '@mui/joy/Tabs';
import TabList from '@mui/joy/TabList';
import Tab from '@mui/joy/Tab';
import TabPanel from '@mui/joy/TabPanel';

// 필요한 prop을 인자로 전달받습니다.
const HerbDetailIndex = ({ selectedTab, handleTabChange, handleMyHerbsClick }) => {
  return (
    <Tabs aria-label="Herb details" value={selectedTab} onChange={handleTabChange}>
      <TabList>
        <Tab>상세 설명</Tab>
        <Tab onClick={handleMyHerbsClick}>내가 찍은 이미지</Tab>
      </TabList>
      <TabPanel value={0}>
        {/* 상세 설명 컨텐츠 */}
        <Typography level="body1">이곳에는 식물의 상세 설명이 표시됩니다.</Typography>
      </TabPanel>
      <TabPanel value={1}>
        {/* 내가 찍은 이미지 컨텐츠 */}
        <Typography level="body1">이곳에는 사용자가 찍은 식물의 사진이 표시됩니다.</Typography>
      </TabPanel>
    </Tabs>
  );
};

export default HerbDetailIndex;