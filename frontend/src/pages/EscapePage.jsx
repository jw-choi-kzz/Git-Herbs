import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import Weather from "../components/escape/Weather";
import WildlifeArea from "../components/escape/WildlifeArea";
import SeasonalHerbs from "../components/escape/SeasonalHerbs";
import BehaviorTips from "../components/escape/BehaviorTips";
import "../components/escape/escape.css";
import axios from "axios";
import useGlobalStyles from '../utils/useGlobalStyles';
import Monthtip from "../components/escape/Monthtip";



const useGlobalStyle = () => {
  const [weatherdata, setWeather] = useState(null);


  const getCurrentLocation = () => {
    navigator.geolocation.getCurrentPosition((position) => {
      let lat = position.coords.latitude;
      let lon = position.coords.longitude;
      getWeatherByCurrentLocation(lat, lon);
    });
  };




  const getWeatherByCurrentLocation = async (lat, lon) => {
    let url = `https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lon}&appid=&units=metric`;
    let response = await fetch(url);
    let data = await response.json();
    setWeather(data);
  };

  useEffect(() => {
    getCurrentLocation();
  }, []);

  return weatherdata; // 
};
//overflowY: 'auto', height: 'calc(100vh - 105px)',
const EscapePage = () => {
  const weatherdata = useGlobalStyle(); 
  useGlobalStyles(); 
  return (
    <div style={{ justifyContent: 'center' }}>
      <>
      <br />
        <Weather weatherdata={weatherdata}></Weather>
        <br />
        <p>야생동물 서식 구역</p>
        <WildlifeArea weatherdata={weatherdata} ></WildlifeArea>
        <br /><hr color="e8e8e8" width="90%"/>
        <p>제철약초</p>
        <SeasonalHerbs></SeasonalHerbs>
        <br /><hr color="e8e8e8" width="90%"/>
        <p>위급 상황시 행동 요령</p>
        <BehaviorTips></BehaviorTips>
        <br /><hr color="e8e8e8" width="90%"/>
        <p>월별 Tip</p>
        <Monthtip></Monthtip>
      </>
      <br></br>
      <br></br>
    </div>
  );
};

export default EscapePage;
