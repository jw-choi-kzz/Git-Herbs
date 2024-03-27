import { useEffect, useState, useContext } from "react";
import { useNavigate } from "react-router-dom";
import styled from 'styled-components';
import BedgeList from "../components/mypage/BedgeList";
const Container = styled.div`
    padding: 24px;
`;
const BedgePage = () => {

        // const [bedgeList] = useState([
        //     {
        //       badgeId: 1,
        //       badgeTitle: "나는 약초꾼이다",
        //       badgeInfo: "Git Herbs 가입",
        //       check: true,
        //     },
        //     {
        //         badgeId: 2,
        //         badgeImg: "첫 약초 판별",
        //         check: true,
        //         date: "2024-01-01",
        //     },
        //     {
        //         badgeId: 3,
        //         badgeImg: "네임드 심마니",
        //         check: false,
        //         date: null,
        //     },
        //     {
        //         badgeId: 4,
        //         badgeImg: "첫 약초 판별",
        //         check: false,
        //         date: null,
        //     },
        // ]);

    return (
        <Container>
            <BedgeList/>
        </Container>
    )
}

export default BedgePage;