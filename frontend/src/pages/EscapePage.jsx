import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import Weather from "../components/escape/Weather";

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

  return (
    <div style={{ overflowY: 'auto', height: 'calc(100vh - 131px)',  justifyContent: 'center' }}>
      <>
        <p>EscapePage</p>
        <br />
        <Weather weatherdata={weatherdata}></Weather>
      </>
    </div>
  );
};

export default EscapePage;
