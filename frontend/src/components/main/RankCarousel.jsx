import { useState } from "react";
import { Carousel } from 'antd';
import styled from 'styled-components';

const Content = styled.div`
  height: 160px;
  color: #21351F;
  line-height: 160px;
  text-align: center;
  background: #DDE5B6;
  position: relative;
  padding: 10px;
  box-sizing: border-box;
`;

const RankTitle = styled.div`
  margin: 0;
  line-height: 1.5em;
  font-size: 1.5em;
`;

const Nickname = styled.div`
  position: absolute;
  bottom: 10px;
  left: 10px;
`;

const ProfileImage = styled.img`
  border-radius: 50%;
  object-fit: contain;
  width: 100px;
  height: 100px;
  margin: 0.5em 0;
`;

export default function RankCarousel() {
  const [currentSlide, setCurrentSlide] = useState(0);
  // let idx = 0;
  
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
            <img src="/logo.svg" className="h-12 cursor-pointer" alt="Logo"/>
            <RankTitle>{index + 1}등</RankTitle>
            <Nickname>{rankUser.nickname}</Nickname>
            <ProfileImage src={rankUser.profileUrl} alt={rankUser.nickname} />
          </Content>
        ))}
      </Carousel>
    </>
  );
}
