import React from "react";
import styled from "styled-components";
import HerbMap from "./HerbMap";

const Container = styled.div`
  padding: 0 20px 20px;
  max-width: 320px;
  background-color: #f5f5f5;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const Section = styled.div`
  background-color: #fff;
  width: 95%;
  padding: 5px;
  border-radius: 16px;
  margin-bottom: 10px;
`;

const HeaderContainer = styled.div`
  display: flex;
  justify-content: space-between;
  width: 100%;
  align-items: center;
`;

const SectionHeader = styled.div.attrs({
  className: "bold",
})`
  font-size: 18px;
  //font-family: "SpoqaHanSansNeo", sans-serif;
  color: #305f72; 
  //font-weight: 500;
  margin-bottom: 4px;
  margin-left: 5px;
  margin-top: 5px;
`;

const SectionSubHeader = styled.div.attrs({
  className: "medium",
})`
  font-size: 10px;
  color: #888;
  margin-bottom: 0px;
  margin-right: 5px;
`;

const SectionDivisionLine = styled.div`
  border-top: 1px solid #444;
  margin-bottom: 5px;
`;

const Content = styled.div`
  font-size: 14px;
  color: #333;
  margin-top: 8px;
  margin-left: 6px;
`;

const ImageStyle = styled.img`
  width: 60%;
  height: auto;
  margin-bottom: 10px;
  border-radius: 8px;
`;

const StyledList = styled.ul`
  list-style: none;
  padding-left: 0;
  margin: 0;
`;

const ListItem = styled.li`
  font-size: 14px;
  color: #333;
  padding-left: 15px;
  text-indent: -15px;
  padding-bottom: 3px;
  padding-top: 3px;

  &:before {
    content: "•";
    padding-right: 7px;
    padding-left: 3px;
    color: #305f72;
  }
`;

const HerbInfoBox = ({ data }) => {
  if (!data || !Array.isArray(data.herMedicinalEffects)) {
    return <div>로딩 중...</div>;
  }
  const {
    herbId,
    herbName,
    herbImgId,
    herbNickname,
    herbMedicalPart,
    herbHarvestingTime,
    herbEnvironment,
    herbQuality,
    herMedicinalEffects = [],
  } = data;

  const imageUrl = herbImgId ? herbImgId : "/herbs/002_plant.png";

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
        <Content>
          <HerbMap data={{ herbId }} />
        </Content>
      </Section>
    </Container>
  );
};

export default HerbInfoBox;
