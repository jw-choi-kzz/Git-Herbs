import { useEffect, useState, useContext } from "react";
import { useNavigate } from "react-router-dom";
import styled from 'styled-components';
import BedgeList from "../components/mypage/BedgeList";
const Container = styled.div`
    padding: 24px;
`;
const BedgePage = () => {
    return (
        <Container>
            <BedgeList />
        </Container>
    )
}

export default BedgePage;