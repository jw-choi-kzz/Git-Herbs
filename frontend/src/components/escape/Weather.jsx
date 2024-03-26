// import { Container } from '@mui/material';
import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import axios from 'axios';
import proj4 from 'proj4';



proj4.defs("EPSG:5178", "+proj=tmerc +lat_0=38 +lon_0=127.5 +k=0.9996 +x_0=1000000 +y_0=2000000 +ellps=GRS80 +units=m +no_defs");

const Weather = ({ weatherdata }) => {
  const [regionCode, setRegionCode] = useState(null);
  const [dust, setDust] = useState(null);
  const [dustStatus, setDustStatus] = useState(null);




  useEffect(() => {
    if (weatherdata && weatherdata.coord) {
      const x = weatherdata.coord.lon;
      const y = weatherdata.coord.lat;
      const tmCoordinates = convertToTM(x, y);
  
      const API_KEYY = "iD8ytnvj01PuwxK775AYDmkbHlOUPl6SJwubVeh6ujfwtnzThiJ%2FCWpyK6echmvwNG2%2Fz0%2BGE1j5%2FuA6IaodVg%3D%3D";
      const config = {
        headers: {
          'Authorization': `KakaoAK 8a18ae99d48d2eee9185b60c07d99e85`
        }
      };

      axios.get(`https://dapi.kakao.com/v2/local/geo/coord2regioncode.json?x=${x}&y=${y}`, config)
        .then(response => {
          setRegionCode(response.data);
          axios.get(`http://apis.data.go.kr/B552584/MsrstnInfoInqireSvc/getNearbyMsrstnList?tmX=${tmCoordinates[0]}&tmY=${tmCoordinates[1]}&returnType=JSON&serviceKey=${API_KEYY}&ver=1.2`)
            .then(response => {
              // console.log(response.data.response.body.items[0].stationName);

              axios.get(`http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty?serviceKey=${API_KEYY}&returnType=JSON&numOfRows=1&pageNo=1&dataTerm=DAILY&ver=1.2&stationName=${response.data.response.body.items[0].stationName}
            `)
                .then(response => {
                  const so2Grade = response.data.response.body.items[0].so2Grade;
                  const dustStatus = getDustStatus(so2Grade);

                  setDust(dustStatus);
                })
                .catch(error => {

                  console.error('Error:', error);
                });


            })
            .catch(error => {

              console.error('Error:', error);
            });
        })
        .catch(error => {

          console.error('Error:', error);
        });
    }

    // console.log(dustStatus)
  }, [weatherdata]);

  const convertToTM = (longitude, latitude) => {
    const tmCoordinates = proj4(proj4.defs['EPSG:5178'], [longitude, latitude]);
    return tmCoordinates;
  };
  const getDustStatus = (so2Grade) => {
    const statusMap = {
        1: "좋음",
        2: "보통",
        3: "나쁨",
        4: "매우나쁨"
    };
    return statusMap[so2Grade] || "정보없음";
};


  if (!weatherdata || weatherdata.length === 0) {
    return null; // or handle loading state
  }
  const icon = weatherdata.weather[0].icon;
  const id = weatherdata.weather[0].id;
  const type = wDescEngToKor(id);
  const position = "https://cdn.builder.io/api/v1/image/assets/TEMP/2f5a5b725a8f55ae42b346fc1cee79c75663d245156e22d2f0005cc89def8553?";




  function wDescEngToKor(w_id) {
    var w_id_arr = [201, 200, 202, 210, 211, 212, 221, 230, 231, 232,
      300, 301, 302, 310, 311, 312, 313, 314, 321, 500,
      501, 502, 503, 504, 511, 520, 521, 522, 531, 600,
      601, 602, 611, 612, 615, 616, 620, 621, 622, 701,
      711, 721, 731, 741, 751, 761, 762, 771, 781, 800,
      801, 802, 803, 804, 900, 901, 902, 903, 904, 905,
      906, 951, 952, 953, 954, 955, 956, 957, 958, 959,
      960, 961, 962];
    var w_kor_arr = ["천둥구름", "천둥구름", "폭우", "천둥구름",
      "천둥구름", "천둥구름", "천둥구름", "천둥구름", "천둥구름",
      "안개비", "안개비", "안개비", "안개비", "적은비", "적은비",
      "적은비", "소나기", "소나기", "소나기", "악한 비", "중간 비", "강한 비",
      "강한 비", "폭우", "우박", "소나기", "소나기", "소나기", "소나기",
      "가벼운 눈", "눈", "눈", "진눈깨비", "소나기", "비와 눈", "비와 눈", "비,눈",
      "비,눈", "비, 눈", "박무", "연기", "연무", "모래 먼지", "안개", "모래", "먼지", "화산재", "돌풍",
      "토네이도", "맑음", "살짝흐림", "흐림", "맑음",
      "흐림", "토네이도", "태풍", "허리케인", "한랭", "고온", "바람부는", "우박", "바람없는",
      "약한 바람", "바람", "중간 바람", "신선한 바람", "센 바람", "돌풍", "돌풍",
      "돌풍", "폭풍", "폭풍", "허리케인"];
    for (var i = 0; i < w_id_arr.length; i++) {
      if (w_id_arr[i] == w_id) {
        return w_kor_arr[i];
        break;
      }
    }
    return "none";
  }


  const weathericon = `http://openweathermap.org/img/wn/${icon}@2x.png`;
  
  return (
    <>
      <Container>
        <div className="flex gap-5 self-center px-5 pb-2.5 mt-7 font-medium bg-white rounded-xl shadow-sm text-neutral-800">
          <Div2>
            <Img2
              loading="lazy"
              src={weathericon}
              className="shrink-0 w-6 aspect-square"
            />
            <Div3>{weatherdata.main.temp} °C</Div3>
            <Img
              loading="lazy"
              src={position}
              className="shrink-0 w-6 aspect-square"
            />
            <Div4>{regionCode && regionCode.documents && regionCode.documents.length > 0 && regionCode.documents[0].region_2depth_name}</Div4>
          </Div2>
          <Div6>
          <Div5>미세먼지 : {dust}</Div5>
          <Div5 className= "my-auto">{type}</Div5>

          </Div6>
        </div>
      </Container>
    </>
  );
};

export default Weather;


const Container = styled.div`
  width: 280px;
  height: auto; /* Changed to auto to wrap content */
  margin: auto;
  border-radius: 10px;
  padding: 10px;
  box-sizing: border-box;
  background-color: #fff;
  box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
  display: flex;
  flex-direction: column;
  align-items: flex-start; /* Align items to start */
  cursor: pointer;
  
`;


const Div2 = styled.div`
  display: flex;
  gap: 11px;
  font-size: 24px;
  color: #407700;
  font-weight: 700;
`;

const Img = styled.img`
  aspect-ratio: 1;
  object-fit: auto;
  object-position: center;
  width: 25px;
`;



const Img2 = styled.img`
  aspect-ratio: 1;
  object-fit: auto;
  object-position: center;
  width: 45px;
`;



const Div3 = styled.div`
  font-family: Spoqa Han Sans Neo, sans-serif;
  flex-grow: 1;
  flex-basis: auto;
  margin: auto 0;
  padding : 0px 10px 0px 0px
`;

const Div4 = styled.div`
  font-family: Spoqa Han Sans Neo, sans-serif;
  flex-grow: 1;
  flex-basis: auto;
  margin: auto 0;

`;


const Div5 = styled.div`
  font-family: Spoqa Han Sans Neo, sans-serif;
  flex-grow: 1;
  flex-basis: auto;
  margin: auto 0;
  padding : 0px 30px 0px 10px;

`;


const Div6 = styled.div`
  display: flex;
  gap: 8px;
  font-size: 14px;

  font-weight: 700;
  // flex-direction: row; /* 아이템을 가로로 나란히 표시 */
  justify-content: space-between;
`;
