import { useEffect, useRef } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import axios from "axios";

const LoginLoad = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const initialRender = useRef(true);

  useEffect(() => {
    if (initialRender.current) {
      initialRender.current = false;
      loginProcess();
    }
  }, []);

  const loginProcess = async () => {
    const params = new URLSearchParams(location.search);
    const code = params.get("code");
    const status = params.get("status");
    const originalPath = params.get("state");

    console.log(code);
    console.log(status);
    console.log(originalPath);

    try {
      const response = await axios.get(
        `/v1/user/login?code=${code}&status=${status}&state=${encodeURIComponent(
          originalPath
        )}`
      );

      const redirectUrl = response.data.redirectUrl;

      window.location.href = redirectUrl;
    } catch (error) {
      console.error("Login process failed", error);
      navigate("/");
    }
  };

  return (
    <>
      <p>로그인 처리 중...</p>
    </>
  );
};

export default LoginLoad;
