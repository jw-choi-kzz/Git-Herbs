import { useEffect, useState, useContext } from "react";
import { useNavigate, useRouteError } from "react-router-dom";
import { configService } from "../apis/config";
import { userServcie } from "../apis/user";

const ErrorPage = () => {
    const error = useRouteError();
    const navigate = useNavigate();

    useEffect(() => {
        // console.log("ErrorPage", error);
        const reissueConfig = configService.reissueconfig();
        userServcie.reissueToken(reissueConfig)
        .then((res) => {
            const access = res.data.data.access_token;
            const refresh = res.data.data.refresh_token;
            localStorage.setItem('accessToken', access);
            localStorage.setItem('refreshToken', refresh);
            navigate("/");
        }).catch(() => {
            localStorage.clear();
            navigate("/");
        });
        // localStorage.clear();
        // navigate("/");
    }, []);
    return (
        <>
            <p>ErrorPage</p>
        </>
    )
}

export default ErrorPage;