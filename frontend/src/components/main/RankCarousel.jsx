import { useState } from "react";
import { Carousel } from 'antd';
import styled, { css } from 'styled-components';

const baseStyle = css`
  font-family: 'SpoqaHanSansNeo', sans-serif;
`;

const Content = styled.div`
  ${baseStyle}
  height: 160px;
  color: #21351F;
  background: #DDE5B6;
  position: relative;
  padding: 20px;
  box-sizing: border-box;
`;

const BadgeTitle = styled.div`
  ${baseStyle}
  z-index: 2;
  text-align: left;
  font-size: 1.1em;
  margin: 8px 0;
  position: absolute;
  top: 40px;
`;

const RankTitle = styled.div`
  ${baseStyle}
  z-index: 2;
  margin: 0;
  position: absolute;
  top: 80px; // Adjust as necessary
  // right: 10px;
  text-align: right;
  font-size: 1.3em;
`;

const Nickname = styled.div`
  ${baseStyle}
  z-index: 2;
  position: absolute;
  top: 110px; // Adjust as necessary
  text-align: right;
  font-size: 1.3em;
  // right: 10px;
`;

const ProfileImage = styled.img`
  ${baseStyle}
  z-index: 1;
  border-radius: 50%;
  object-fit: cover; // Changed to 'cover' to ensure a perfect circle
  width: 100px; // Reduced size as per design
  height: 100px; // Reduced size as per design
  position: absolute;
  right: 10px;
  top: 50%;
  transform: translateY(-50%);
`;

export default function RankCarousel() {
  
  const rankList = [
    {
      userId: 1,
      nickname: "아구몬",
      profileUrl: "/profileimg/아구몬.webp"
    },
    {
      userId: 2,
      nickname: "최고봉심마니",
      profileUrl: "/profileimg/최고봉심마니.png"
    },
    {
      userId: 3,
      nickname: "부아아앙",
      profileUrl: "/profileimg/부아아앙.jpg"
    }
  ];

  return (
    <>
      <Carousel autoplay>
        {rankList.map((rankUser, index) => (
          <Content key={index}>
            <div>
            <img src="/logo.svg" className="h-12 cursor-pointer" style={{ width: '30%' }} alt="Logo"/>
            <BadgeTitle>3월 뱃지왕</BadgeTitle>
            {/* <div style={{textAlign: "right"}}> */}
            <RankTitle className="bold">{index + 1}등</RankTitle>
            <Nickname className="medium">{rankUser.nickname}</Nickname>
            {/* </div> */}
            </div>
            <div>
            <ProfileImage src={rankUser.profileUrl} alt={rankUser.nickname} />
            </div>
          </Content>
        ))}
      </Carousel>
    </>
  );
}
