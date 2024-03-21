import { useEffect, useState, useContext } from "react";
import { useNavigate } from "react-router-dom";
import RankCarousel from "../components/main/RankCarousel";
import TodayHerb from "../components/main/TodayHerb";
import HerbQuiz from "../components/main/HerbQuiz";

const MainPage = () => {
    return (
        <div style={{ overflowY: 'auto', height: 'calc(100vh - 131px)' }}>
        <RankCarousel />
            <br />
            <TodayHerb />
            <br />
            <HerbQuiz />
        </div>
    )
}

export default MainPage;