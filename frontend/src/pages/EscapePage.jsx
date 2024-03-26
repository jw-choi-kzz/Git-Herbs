import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import Weather from "../components/escape/Weather";
import WildlifeArea from "../components/escape/WildlifeArea";
import SeasonalHerbs from "../components/escape/SeasonalHerbs";
import BehaviorTips from "../components/escape/BehaviorTips";
import "../components/escape/escape.css";
import axios from "axios";

const useGlobalStyles1 = () => {
  useEffect(() => {
    const style = document.createElement("style");
    style.innerHTML = `
      body {
        overflow-y: auto;
      }
      body::-webkit-scrollbar,
      body *::-webkit-scrollbar {
        display: none; /* Hide scrollbar for Chrome, Safari and Opera */
      }
      body,
      body * {
        -ms-overflow-style: none; /* IE and Edge */
        scrollbar-width: none; /* Firefox */
      }
      @keyframes fadeIn {
        from {
          opacity: 0;
        }
        to {
          opacity: 1;
        }
      }
    `;
    document.head.appendChild(style);
    return () => {
      document.head.removeChild(style);
    };
  }, []);
};



const useGlobalStyles = () => {
  const [weatherdata, setWeather] = useState(null);


  const getCurrentLocation = () => {
    navigator.geolocation.getCurrentPosition((position) => {
      let lat = position.coords.latitude;
      let lon = position.coords.longitude;
      getWeatherByCurrentLocation(lat, lon);
    });
  };




  const getWeatherByCurrentLocation = async (lat, lon) => {
    let url = `https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lon}&appid=17b9703e65beccec33b5a18c5c49c021&units=metric`;
    let response = await fetch(url);
    let data = await response.json();
    setWeather(data);
  };

  useEffect(() => {
    getCurrentLocation();
  }, []);

  return weatherdata; // 
};

const EscapePage = () => {
  const weatherdata = useGlobalStyles(); 
  useGlobalStyles1(); 
  return (
    <div style={{ overflowY: 'auto', height: 'calc(100vh - 131px)',  justifyContent: 'center' }}>
      <>
      <br></br>
        <Weather weatherdata={weatherdata}></Weather>
        <p>야생동물 서식 구역</p>
        <WildlifeArea></WildlifeArea>
        <p>제철약초</p>
        <SeasonalHerbs></SeasonalHerbs>
        <p>위급 상황시 행동 요령</p>
        <BehaviorTips></BehaviorTips>
      </>
    </div>
  );
};

export default EscapePage;
