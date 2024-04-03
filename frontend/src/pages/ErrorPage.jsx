import { useEffect, useState, useContext } from "react";
import { useNavigate } from "react-router-dom";

const ErrorPage = () => {

    useEffect(() => {
        localStorage.clear();
        navigate("/");
    }, []);
    return (
        <>
            <p>ErrorPage</p>
        </>
    )
}

export default ErrorPage;