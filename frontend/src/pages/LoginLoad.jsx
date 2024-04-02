import { useEffect, useRef } from "react";
import { useNavigate, useLocation, useSearchParams } from "react-router-dom";
import axios from "axios";
import useLoginStore from "../store/useLoginStore";

const LoginLoad = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const initialRender = useRef(true);
  const [searchParams] = useSearchParams();
  const url = "https://j10a205.p.ssafy.io/api/v1/user/token";
  const { setLogin, setUserId, setProfileImg, setAccessToken, setRefreshToken } = useLoginStore();

  const data = {
    code: searchParams.get("code"),
    error: searchParams.get("error"),
    error_description: searchParams.get("error_description"),
    state: searchParams.get("state"),
  }
  console.log(data);

  const loginProcess = () => {
    axios.post(url, data)
      .then(response => {
        console.log("Login Response:", response);
        const { accessToken, refreshToken, userId, profileImg } = response.data.data;
        
        localStorage.setItem("userId", userId);
        localStorage.setItem("profileImg", profileImg);
        localStorage.setItem("accessToken", accessToken);
        localStorage.setItem("refreshToken", refreshToken);

        setLogin(true);
        setUserId(userId);
        setProfileImg(profileImg);
        setAccessToken(accessToken);
        setRefreshToken(refreshToken);
        navigate('');
      })
      .catch(error => {
        console.error("Login Error:", error);
      });
  };

  useEffect(() => {
    if (initialRender.current) {
      initialRender.current = false;
      loginProcess();
    }
  }, []);

  return (
    <>
      <p>로그인 처리 중...</p>
    </>
  );
};

export default LoginLoad;
