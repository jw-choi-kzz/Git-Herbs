import { useEffect, useState, useContext } from "react";
import { useNavigate } from "react-router-dom";
import RankCarousel from "../components/main/RankCarousel";
import TodayHerb from "../components/main/TodayHerb";
import HerbQuiz from "../components/main/HerbQuiz";
const useGlobalStyles = () => {
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

  
const MainPage = () => {
    useGlobalStyles(); 
    return (
        <div style={{ overflowY: 'auto', height: 'calc(100vh - 131px)',  justifyContent: 'center' }}>
          <RankCarousel />
          <br />
          <TodayHerb />
          <br />
          <HerbQuiz />
        </div>
    )
}

export default MainPage;