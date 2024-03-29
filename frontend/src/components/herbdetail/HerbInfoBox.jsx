import React from 'react';
import styled from 'styled-components';

const Container = styled.div`
  padding: 20px;
  max-width: 320px;
  background-color: #F5F5F5;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  display: flex; /* 새로 추가된 스타일 */
  flex-direction: column; /* 새로 추가된 스타일 */
  align-items: center; /* 새로 추가된 스타일 */
`;

const Section = styled.div`
  background-color: #fff;
  width: 95%;
  padding: 5px;
  border-radius: 16px;
  margin-bottom: 20px;
`;

// 헤더들을 감쌀 컨테이너에 대한 스타일
const HeaderContainer = styled.div`
  display: flex;
  justify-content: space-between;
  width: 100%; // 컨테이너의 너비를 섹션 너비와 같게 설정합니다.
  align-items: center; // 세로축 중앙 정렬
`;

const SectionHeader = styled.div`
  font-size: 16px;
  font-weight: bold;
  color: #305F72;
  margin-bottom: 4px;
`;

const SectionSubHeader = styled.div`
  font-size: 10px;
  color: #888;
  margin-bottom: 10px;
`;

const SectionDivisionLine = styled.div`
  border-top: 1px solid #444;
  margin-bottom: 5px;
`;

const Content = styled.div`
  font-size: 14px;
  color: #333;
`;

const ImageStyle = styled.img`
  width: 60%;
  height: auto;
  margin-bottom: 10px;
  border-radius: 8px;
`;

// 리스트에 적용할 스타일 컴포넌트
const StyledList = styled.ul`
  list-style: none;
  padding-left: 0;
  margin: 0;
`;

// 리스트 항목에 적용할 스타일 컴포넌트
const ListItem = styled.li`
  font-size: 14px;
  color: #333;
  padding-left: 15px;
  text-indent: -15px;

  &:before {
    content: "•";  // 항목 앞에 점을 추가합니다.
    padding-right: 10px;
    color: #305F72; // 점의 색상을 설정합니다.
  }
`;

const HerbInfoBox = ({ data }) => {
  if (!data || !Array.isArray(data.herMedicinalEffects)) {
    return <div>로딩 중...</div>; // 또는 적절한 오류 메시지
  }
  const {
    herbName,
    herbImgId, // 이 이미지 ID를 실제 경로로 매핑하는 로직이 필요
    herbNickname,
    herbMedicalPart,
    herbHarvestingTime,
    herbEnvironment,
    herbQuality,
    herMedicinalEffects = [] // 기본값으로 빈 배열 할당
  } = data;

  const imageUrl = herbImgId ? herbImgId : '/herbs/002_plant.png';

  return (
    <Container>
      <ImageStyle src={imageUrl} alt={herbName} />
      <Section>
        <HeaderContainer>
          <SectionHeader>다른 이름</SectionHeader>
          <SectionSubHeader>Other Names</SectionSubHeader>
        </HeaderContainer>
        <SectionDivisionLine></SectionDivisionLine>
        <Content>{herbNickname}</Content>
      </Section>
      <Section>
        <HeaderContainer>
          <SectionHeader>약효</SectionHeader>
          <SectionSubHeader>Medicinal Properties</SectionSubHeader>
        </HeaderContainer>
        <SectionDivisionLine></SectionDivisionLine>
        {/* 약효는 리스트로 주어진다고 가정하여 map을 사용 */}
        <StyledList>
        {herMedicinalEffects.map((effect, index) => (
      <ListItem key={index}>{effect.medicinalEffect}</ListItem>
    ))}
        </StyledList>
      </Section>
      <Section>
        <HeaderContainer>
          <SectionHeader>생태</SectionHeader>
          <SectionSubHeader>Ecology</SectionSubHeader>
        </HeaderContainer>
        <SectionDivisionLine></SectionDivisionLine>
        <Content>{herbEnvironment}</Content>
      </Section>
      <Section>
        <HeaderContainer>
          <SectionHeader>채취시기</SectionHeader>
          <SectionSubHeader>Harvest Period</SectionSubHeader>
        </HeaderContainer>
        <SectionDivisionLine></SectionDivisionLine>
        <Content>{herbHarvestingTime}</Content>
      </Section>
      <Section>
        <HeaderContainer>
          <SectionHeader>약용부위</SectionHeader>
          <SectionSubHeader>Used Parts</SectionSubHeader>
        </HeaderContainer>
        <SectionDivisionLine></SectionDivisionLine>
        <Content>{herbMedicalPart}</Content>
      </Section>
      <Section>
        <HeaderContainer>
          <SectionHeader>품질기준</SectionHeader>
          <SectionSubHeader>Quality Standards</SectionSubHeader>
        </HeaderContainer>
        <SectionDivisionLine></SectionDivisionLine>
        <Content>{herbQuality}</Content>
      </Section>
      <Section>
        <HeaderContainer>
          <SectionHeader>위치</SectionHeader>
          <SectionSubHeader>Habitat / Location</SectionSubHeader>
        </HeaderContainer>
        <SectionDivisionLine></SectionDivisionLine>
        <Content>지도 들어갈위치</Content>
      </Section>
    </Container>
  );
};

export default HerbInfoBox;