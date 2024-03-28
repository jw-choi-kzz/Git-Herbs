import React from 'react';
import Tabs from '@mui/joy/Tabs';
import TabList from '@mui/joy/TabList';
import Tab from '@mui/joy/Tab';
import TabPanel from '@mui/joy/TabPanel';
import Typography from '@mui/joy/Typography';

const HerbDetailIndex = ({ selectedTab, onTabChange}) => {
  return (
    <Tabs aria-label="Herb details" value={selectedTab} onChange={onTabChange}>
      <TabList>
        <Tab>상세 설명</Tab>
        <Tab>내가 찍은 이미지</Tab>
      </TabList>
      <TabPanel value={0}>
      </TabPanel>
      <TabPanel value={1}>
      </TabPanel>
    </Tabs>
  );
};

export default HerbDetailIndex;