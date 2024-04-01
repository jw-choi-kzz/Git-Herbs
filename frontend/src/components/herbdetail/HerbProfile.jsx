import React, { useEffect, useState } from "react";
import styled from "styled-components";
import { PiStarFill, PiStarBold } from "react-icons/pi";
import { herbsService } from "../../apis/herbs";
import { configService } from "../../apis/config";
import axios from "axios";

const Container = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin: 12px;
`;

const HerbName = styled.h1`
  font-size: 30px;
  margin-bottom: 0px;
`;

const HerbScientificName = styled.h2`
  font-size: 15px;
  font-weight: normal;
  color: #757575;
`;

// 북마크 상태에 따라 색상을 동적으로 적용할 수 있는 스타일 컴포넌트
const BookmarkIcon = styled.div`
  color: #f49349;
  font-size: 30px;
`;

const HerbProfile = ({ data }) => {
  const [isBookmarked, setIsBookmarked] = useState(false);

  useEffect(() => {
    getBookmark();
  }, [data]);

  const getBookmark = async () => {
    const config = configService.loginConfig();
    herbsService
      .getBookmark(data.herbId, config)
      .then((response) => {
        console.log(response);
        setIsBookmarked(response.data ? 1 : 0);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const removeBookmark = async () => {
    const config = configService.loginConfig();
    herbsService
      .deleteBookmark(data.herbId, config)
      .then((response) => {
        console.log(response);
        setIsBookmarked(0);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const postBookmark = async () => {
    const config = configService.loginConfig(); // configService에서 request header를 가져옴
    axios
      .post(
        `https://j10a205.p.ssafy.io/api/v1/herbs/${data.herbId}/bookmark`,
        null,
        config // configService에서 가져온 request header 설정
      )
      .then((response) => {
        console.log(response);
        setIsBookmarked(1);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const handleClick = async () => {
    if (isBookmarked) {
      await removeBookmark();
    } else {
      await postBookmark();
    }
  };

  return (
    <Container>
      <div>
        <HerbName>{data.herbName}</HerbName>
        <HerbScientificName>{data.herbScientificName}</HerbScientificName>
      </div>
      <BookmarkIcon onClick={handleClick}>
        {isBookmarked ? <PiStarFill /> : <PiStarBold />}
      </BookmarkIcon>
    </Container>
  );
};

export default HerbProfile;
