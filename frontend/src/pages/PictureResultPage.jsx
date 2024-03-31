import { useEffect, useState, useContext } from "react";
import { useLocation, useNavigate } from "react-router-dom";

const PictureResultPage = () => {
    const location = useLocation();
    const data = location.state?.responseData;
    console.log(data);
    return (
        <>
            <p>PictureResultPage</p>
        </>
    )
}

export default PictureResultPage;