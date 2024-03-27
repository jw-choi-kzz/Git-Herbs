import { useState } from "react";
import { Carousel } from 'antd';
import styled, { css } from 'styled-components';
import { Link } from "react-router-dom";



export default function SeasonalHerbs() {


  const herbList = [
    {
      herbId: 1,
      herbName: "쑤욱쑥",
      imgId: "https://i.ibb.co/Ybdyn7R/image.png"
    },
    {
      herbId: 2,
      herbName: "만드라고라",
      imgId: "https://i.ibb.co/jzbWCDq/image.png"
    },
    {
      herbId: 3,
      herbName: "당귀",
      imgId: "https://i.ibb.co/jvF7mGt/image.png"
    }
  ];

  return (
    <>
      <Container>
        <Carousel autoplay>
          {herbList.map((herb, index) => (
            <Content key={index}>
              <div>
                <div>
                  <ProfileImage src={herb.imgId} />
                </div>

                <Nickname className="medium">{herb.herbName}</Nickname>
                <Link  to={`/detail/${herb.herbId}`}>
                <Nickname2 > 상세 보기</Nickname2>
                </Link>
              </div>
            </Content>
          ))}
        </Carousel>
      </Container>
    </>
  );
}
const Container = styled.div`
  width: 320px;
  height: auto; /* Changed to auto to wrap content */
  margin: auto;
  border-radius: 10px;
  padding: 10px;
  box-sizing: border-box;
  background-color: #fff;
  box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
  display: flex;
  flex-direction: column;
  // align-items: flex-start; /* Align items to start */
  cursor: pointer;
  
`;

const baseStyle = css`
  font-family: 'SpoqaHanSansNeo', sans-serif;
`;

const Content = styled.div`
  ${baseStyle}
  height: 160px;
  color: #21351F;
  position: relative;
  padding: 20px;
  box-sizing: border-box;
`;




const Nickname = styled.div`
  ${baseStyle}
  z-index: 2;
  position: absolute;
  top: 25px; 
  text-align: right;
  font-size: 1.4em;
  font-weight: bold;
  // right: 10px;
`;



const Nickname2 = styled.div`
  ${baseStyle}
  z-index: 2;
  position: absolute;
  top: 110px; // Adjust as necessary
  text-align: left;
  font-size: 1.1em;
  // font-weight: bold;
  right: 170px;
`;


const ProfileImage = styled.img`
  ${baseStyle}
  // z-index: 1;
  border-radius: 30%;
  object-fit: cover; // Changed to 'cover' to ensure a perfect circle
  width: 120px; // Reduced size as per design
  height: 120px; // Reduced size as per design
  position: absolute;
  right: 10px;
  top: 50%;
  transform: translateY(-50%);
`;