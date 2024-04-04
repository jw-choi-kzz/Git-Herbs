import { useEffect, useState, useContext } from "react";
import { useNavigate } from "react-router-dom";
import styled from 'styled-components';
import BadgeList from "../components/mypage/BadgeList";
const Container = styled.div`
    padding: 24px;
`;
const BadgePage = () => {

    return (
        <Container>
            <BadgeList/>
        </Container>
    )
}

export default BadgePage;